package com.example.demo.app.Test;

import com.example.demo.core.exceptionHandler.BasicSpecificationException;

public enum TestException implements BasicSpecificationException {
    BY_TEST_NAME_NOT_FOUND ("error.notFoundByTestName","401"),
    ;

    private String message;
    private String code;

    TestException(String message, String code) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getErrorCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
