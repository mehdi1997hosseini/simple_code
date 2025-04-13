package com.example.demo.Test;

import com.example.demo.core.exceptionHandler.BasicException;

public enum TestException implements BasicException {
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
