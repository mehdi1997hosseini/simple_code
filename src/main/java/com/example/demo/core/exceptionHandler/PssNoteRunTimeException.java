package com.example.demo.core.exceptionHandler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Locale;
import java.util.Objects;

@Getter
public class PssNoteRunTimeException extends RuntimeException {
    private final BasicException error;
    private Locale localLang;
    private String detail;
    private Object[] digits;
    private HttpStatus httpStatus;

    /*******************************************/
    /********** IS LANG IN CONSTRUCTOR *********/
    /*******************************************/

    public PssNoteRunTimeException(BasicException error, ResponseLangType responseLangType) {
        super(error.getMessage());
        this.error = error;
        this.localLang = Objects.requireNonNullElse(responseLangType, ResponseLangType.EN).getLocaleLanguage();
        this.httpStatus = HttpStatus.OK;
    }

    public PssNoteRunTimeException(BasicException error, ResponseLangType responseLangType, String detail, HttpStatus httpStatus) {
        super(error.getMessage());
        this.error = error;
        this.localLang = Objects.requireNonNullElse(responseLangType, ResponseLangType.EN).getLocaleLanguage();
        this.detail = detail;
        this.httpStatus = httpStatus;
    }

    public PssNoteRunTimeException(BasicException error, ResponseLangType responseLangType, String detail) {
        super(error.getMessage());
        this.error = error;
        this.localLang = Objects.requireNonNullElse(responseLangType, ResponseLangType.EN).getLocaleLanguage();
        this.detail = detail;
        this.httpStatus = HttpStatus.OK;
    }

    public PssNoteRunTimeException(BasicException error, ResponseLangType responseLangType, String detail, HttpStatus httpStatus, Object... digits) {
        super(error.getMessage());
        this.error = error;
        this.localLang = Objects.requireNonNullElse(responseLangType, ResponseLangType.EN).getLocaleLanguage();
        this.detail = detail;
        this.httpStatus = httpStatus;
        this.digits = digits;
    }

    public PssNoteRunTimeException(BasicException error, ResponseLangType responseLangType, String detail, Object digit) {
        super(error.getMessage());
        this.error = error;
        this.localLang = Objects.requireNonNullElse(responseLangType, ResponseLangType.EN).getLocaleLanguage();
        this.detail = detail;
        this.httpStatus = HttpStatus.OK;
        this.digits = new Object[]{digit};
    }

    public PssNoteRunTimeException(BasicException error, String detail, HttpStatus httpStatus) {
        super(error.getMessage());
        this.error = error;
        this.localLang = ResponseLangType.EN.getLocaleLanguage();
        this.detail = detail;
        this.httpStatus = httpStatus;
    }

    public PssNoteRunTimeException(BasicException error, ResponseLangType responseLangType, String detail, Object... digits) {
        super(error.getMessage());
        this.error = error;
        this.localLang = Objects.requireNonNullElse(responseLangType, ResponseLangType.EN).getLocaleLanguage();
        this.detail = detail;
        this.httpStatus = HttpStatus.OK;
        this.digits = digits;
    }

    /***********************************************/
    /********** IS NOT LANG IN CONSTRUCTOR *********/
    /***********************************************/

    public PssNoteRunTimeException(BasicException error) {
        super(error.getMessage());
        this.error = error;
        this.localLang = ResponseLangType.EN.getLocaleLanguage();
        this.httpStatus = HttpStatus.OK;
    }


    public PssNoteRunTimeException(BasicException error, String detail) {
        super(error.getMessage());
        this.error = error;
        this.localLang = ResponseLangType.EN.getLocaleLanguage();
        this.detail = detail;
        this.httpStatus = HttpStatus.OK;
    }

    public PssNoteRunTimeException(BasicException error, String detail, HttpStatus httpStatus, Object... digits) {
        super(error.getMessage());
        this.error = error;
        this.localLang = ResponseLangType.EN.getLocaleLanguage();
        this.detail = detail;
        this.httpStatus = httpStatus;
        this.digits = digits;
    }

    public PssNoteRunTimeException(BasicException error, String detail, Object digit) {
        super(error.getMessage());
        this.error = error;
        this.localLang = ResponseLangType.EN.getLocaleLanguage();
        this.detail = detail;
        this.httpStatus = HttpStatus.OK;
        this.digits = new Object[]{digit};
    }

    public PssNoteRunTimeException(BasicException error, String detail, Object... digits) {
        super(error.getMessage());
        this.error = error;
        this.localLang = ResponseLangType.EN.getLocaleLanguage();
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
