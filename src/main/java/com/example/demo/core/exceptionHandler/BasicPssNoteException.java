package com.example.demo.core.exceptionHandler;

public enum BasicPssNoteException implements BasicException {
    BAD_REQUEST("error.badRequest", "400"),
    UNAUTHORIZED("error.unauthorized", "401"),
    ENTERED_VALUE_IS_NOT_VALID("error.valueIsNotValid", "402"),
    AUTHENTICATION("error.authentication", "403"),
    PAGE_NOT_FOUND("error.pageNotFound", "404");

    private String message;
    private String code;

    BasicPssNoteException(String message, String code) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getErrorCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
