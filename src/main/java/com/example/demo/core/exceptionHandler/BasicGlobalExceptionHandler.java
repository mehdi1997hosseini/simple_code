package com.example.demo.core.exceptionHandler;

import com.example.demo.core.config.RequestLanguageContext;
import com.example.demo.core.exceptionHandler.exception.AppRunTimeException;
import com.example.demo.core.exceptionHandler.exception.AppSqlException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.sql.SQLException;
import java.util.Locale;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class BasicGlobalExceptionHandler {

    private final DynamicMessageSource dynamicMessageSource;

    public BasicGlobalExceptionHandler(DynamicMessageSource dynamicMessageSource) {
        this.dynamicMessageSource = dynamicMessageSource;
    }

    @ExceptionHandler(AppRunTimeException.class)
    public ResponseEntity<?> HandlerException(AppRunTimeException ex) {
        String message = dynamicMessageSource.convertMessageByDigits(ex.getMessage(), lang(ex.getLocalLang()), ex.getDigits());
        return buildResponse(new BasicResponseException(message, ex.getError().getErrorCode(), ex.getDetail()), HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));

        BasicAppExceptionType isNotValid = BasicAppExceptionType.ENTERED_VALUE_IS_NOT_VALID;
        String message = dynamicMessageSource.convertMessageByDigits(isNotValid.getMessage(), lang(null), errorMessage);
        BasicResponseException basicResponseException = new BasicResponseException(message, isNotValid.getErrorCode(), errorMessage);
        return buildResponse(basicResponseException, HttpStatus.OK);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolation(ConstraintViolationException ex) {
        String errorMessage = ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));

        BasicAppExceptionType isNotValid = BasicAppExceptionType.ENTERED_VALUE_IS_NOT_VALID;
        String message = dynamicMessageSource.convertMessageByDigits(isNotValid.getMessage(), lang(null), errorMessage);

        BasicResponseException basicResponseException = new BasicResponseException(
                message,
                isNotValid.getErrorCode(),
                errorMessage
        );
        return buildResponse(basicResponseException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLException.class)
    protected ResponseEntity<Object> handleSQLExceptions(SQLException ex) {
        AppSqlException appSqlException = AppSqlException.doJob(ex);
        String message = dynamicMessageSource.convertMessageByDigits(appSqlException.getError().getMessage(), lang(null), appSqlException.getDigits());
        BasicResponseException responseException = new BasicResponseException(message, appSqlException.getError().getErrorCode(), appSqlException.getDetail());
        return buildResponse(responseException, appSqlException.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception ex, HttpServletRequest request) {
        BasicAppExceptionType internalServerError = BasicAppExceptionType.INTERNAL_SERVER_ERROR;
        String message = dynamicMessageSource.convertMessageByDigits(internalServerError.getMessage(), lang(null), (Object) null);

        BasicResponseException responseException = new BasicResponseException(message, internalServerError.getErrorCode(), ex.getMessage(), request.getRequestURI());
        return buildResponse(responseException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<?> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpServletRequest request) {

        BasicAppExceptionType pageNotFound = BasicAppExceptionType.PAGE_NOT_FOUND;
        String message = dynamicMessageSource.convertMessageByDigits(pageNotFound.getMessage(), lang(null), (Object) null);
        BasicResponseException responseException = new BasicResponseException(message, pageNotFound.getErrorCode(), ex.getMessage(), request.getRequestURI());

        return buildResponse(responseException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<?> handleNoResource(NoResourceFoundException ex, HttpServletRequest request) {
        BasicAppExceptionType pageNotFound = BasicAppExceptionType.NO_RESOURCE_FOUND;
        String message = dynamicMessageSource.convertMessageByDigits(pageNotFound.getMessage(), lang(null), (Object) null);
        BasicResponseException responseException = new BasicResponseException(message, pageNotFound.getErrorCode(), ex.getMessage(), request.getRequestURI());

        return buildResponse(responseException, HttpStatus.NOT_FOUND);
    }


    private ResponseEntity<Object> buildResponse(BasicResponseException responseException, HttpStatus status) {
        return new ResponseEntity<>(responseException, status);
    }

    private Locale lang(Locale locale) {
        return locale == null ? RequestLanguageContext.get() : locale;
    }

}
