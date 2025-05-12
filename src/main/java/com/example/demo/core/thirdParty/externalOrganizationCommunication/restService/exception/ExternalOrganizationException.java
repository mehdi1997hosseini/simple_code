package com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.exception;


import com.example.demo.core.exceptionHandler.BasicSpecificationException;

public enum ExternalOrganizationException implements BasicSpecificationException {
    CONNECTION_ERROR_EXTERNAL_ORGANIZATION("connection.error.external.organization", "503"),
    EXTERNAL_ORGANIZATION_NAME_IS_NOT_EXIST("external.organization.name.is.not.exist", "404"),
    FAILED_PROCESS_RESTART_MANUALLY_EXTERNAL_ORGANIZATION("failed.process.restart.manually.external.organization", "500"),
    FAILED_PROCESS_SHOT_DOWN_MANUALLY_EXTERNAL_ORGANIZATION("failed.process.shot.down.manually.external.organization", "501");

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
