package com.example.demo.core.exceptionHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class BasicGlobalExceptionHandler {

    private final DynamicMessageSource dynamicMessageSource;

    public BasicGlobalExceptionHandler(DynamicMessageSource dynamicMessageSource) {
        this.dynamicMessageSource = dynamicMessageSource;
    }

    @ExceptionHandler(PssNoteRunTimeException.class)
    public ResponseEntity<?> HandlerException(PssNoteRunTimeException ex) {
        String message = dynamicMessageSource.convertMessageByDigits(ex.getMessage(), ex.getLocalLang(), ex.getDigits());
        return new ResponseEntity<>(BasicResponseException.builder().localDateTime(LocalDateTime.now()).code(ex.getError().getErrorCode()).message(message)
                .detailMessage(ex.getDetail()).build().toString(), HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        System.out.println("handleValidationExceptions ====> " + ex);
        String errorMessage = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));

        String exception = new PssNoteRunTimeException(BasicPssNoteException.ENTERED_VALUE_IS_NOT_VALID, ResponseLangType.EN, errorMessage).toString();
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

}
