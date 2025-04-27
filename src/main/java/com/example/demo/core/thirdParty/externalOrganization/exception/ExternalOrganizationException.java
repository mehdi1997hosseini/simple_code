package com.example.demo.core.thirdParty.externalOrganization.exception;

import com.example.demo.core.exceptionHandler.BasicSpecificationException;

public enum ExternalOrganizationException implements BasicSpecificationException {
    CONNECTION_ERROR_EXTERNAL_ORGANIZATION("connection.error.external.organization", "503");

    private final String message;
    private final String errorCode;

    ExternalOrganizationException(String message, String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
