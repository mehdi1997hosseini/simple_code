package com.example.demo.core.exceptionHandler;

public enum BasicAppExceptionType implements BasicSpecificationException {
    BAD_REQUEST("error.badRequest", "400"),
    UNAUTHORIZED("error.unauthorized", "401"),
    ENTERED_VALUE_IS_NOT_VALID("Error.valueIsNotValid", "402"),
    AUTHENTICATION("error.authentication", "403"),
    PAGE_NOT_FOUND("error.pageNotFound", "404"),
    INTERNAL_SERVER_ERROR("error.internalServerError","500"),
    NO_RESOURCE_FOUND("error.noResourceFound", "404"),

    FILE_NOT_FOUND_BY_FILE_NAME("error.fileNotFoundByFileName", "404") ,

    PSQL_EXCEPTION("error.psqlException.databaseError", "-150"),
    PSQL_EXCEPTION_DUPLICATE_DATA("error.psqlException.duplicateData", "-151"),
    ENTITY_NOT_FOUND("error.psqlException.dataNotFound", "-152"),

    ;

    private String message;
    private String code;

    BasicAppExceptionType(String message, String code) {
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
