package com.example.demo.core.exceptionHandler.exception;

import com.example.demo.core.exceptionHandler.BasicSpecificationException;
import com.example.demo.core.exceptionHandler.lang.ResponseLangType;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Locale;
import java.util.Objects;

@Getter
public class AppRunTimeException extends RuntimeException {
    private final BasicSpecificationException error;
    private Locale localLang;
    private String detail;
    private Object[] digits;
    private HttpStatus httpStatus;

    /*******************************************/
    /********** IS LANG IN CONSTRUCTOR *********/
    /*******************************************/

    public AppRunTimeException(BasicSpecificationException error, ResponseLangType responseLangType) {
        super(error.getMessage());
        this.error = error;
        this.localLang = responseLangType.getLocaleLanguage();
        this.httpStatus = HttpStatus.OK;
    }

    public AppRunTimeException(BasicSpecificationException error, ResponseLangType responseLangType, String detail, HttpStatus httpStatus) {
        super(error.getMessage());
        this.error = error;
        this.localLang = responseLangType.getLocaleLanguage();
        this.detail = detail;
        this.httpStatus = httpStatus;
    }

    public AppRunTimeException(BasicSpecificationException error, ResponseLangType responseLangType, String detail) {
        super(error.getMessage());
        this.error = error;
        this.localLang = responseLangType.getLocaleLanguage();
        this.detail = detail;
        this.httpStatus = HttpStatus.OK;
    }

    public AppRunTimeException(BasicSpecificationException error, ResponseLangType responseLangType, String detail, HttpStatus httpStatus, Object... digits) {
        super(error.getMessage());
        this.error = error;
        this.localLang = responseLangType.getLocaleLanguage();
        this.detail = detail;
        this.httpStatus = httpStatus;
        this.digits = digits;
    }

    public AppRunTimeException(BasicSpecificationException error, ResponseLangType responseLangType, String detail, Object digit) {
        super(error.getMessage());
        this.error = error;
        this.localLang = responseLangType.getLocaleLanguage();
        this.detail = detail;
        this.httpStatus = HttpStatus.OK;
        this.digits = new Object[]{digit};
    }

    public AppRunTimeException(BasicSpecificationException error, ResponseLangType responseLangType, String detail, Object... digits) {
        super(error.getMessage());
        this.error = error;
        this.localLang = responseLangType.getLocaleLanguage();
        this.detail = detail;
        this.httpStatus = HttpStatus.OK;
        this.digits = digits;
    }

    /***********************************************/
    /********** IS NOT LANG IN CONSTRUCTOR *********/
    /***********************************************/

    public AppRunTimeException(BasicSpecificationException error) {
        super(error.getMessage());
        this.error = error;
        this.httpStatus = HttpStatus.OK;
    }
    public AppRunTimeException(BasicSpecificationException error,Object digit) {
        super(error.getMessage());
        this.error = error;
        this.httpStatus = HttpStatus.OK;
    }

    public AppRunTimeException(BasicSpecificationException error, String detail) {
        super(error.getMessage());
        this.error = error;
        this.detail = detail;
        this.httpStatus = HttpStatus.OK;
    }
    public AppRunTimeException(BasicSpecificationException error, String detail, HttpStatus httpStatus) {
        super(error.getMessage());
        this.error = error;
        this.detail = detail;
        this.httpStatus = httpStatus;
    }
    public AppRunTimeException(BasicSpecificationException error, String detail, HttpStatus httpStatus, Object... digits) {
        super(error.getMessage());
        this.error = error;
        this.detail = detail;
        this.httpStatus = httpStatus;
        this.digits = digits;
    }

    public AppRunTimeException(BasicSpecificationException error, String detail, Object digit) {
        super(error.getMessage());
        this.error = error;
        this.detail = detail;
        this.httpStatus = HttpStatus.OK;
        this.digits = new Object[]{digit};
    }

    public AppRunTimeException(BasicSpecificationException error, String detail, Object... digits) {
        super(error.getMessage());
        this.error = error;
        this.detail = detail;
        this.httpStatus = HttpStatus.OK;
        this.digits = digits;
    }

    @Override
    public String toString() {
        return "{" +
                "message=" + error.getMessage() +
                ", code='" + error.getErrorCode() + '\'' +
                ", detail='" + detail + '\'' +
                ", httpStatus=" + httpStatus +
                '}';
    }
}
