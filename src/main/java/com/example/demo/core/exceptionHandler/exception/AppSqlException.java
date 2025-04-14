package com.example.demo.core.exceptionHandler.exception;

import com.example.demo.core.exceptionHandler.BasicAppExceptionType;
import com.example.demo.core.exceptionHandler.BasicSpecificationException;
import org.springframework.http.HttpStatus;

import java.sql.SQLException;
import java.util.Collections;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;


public class AppSqlException extends SQLException {
    private final BasicSpecificationException error;
    private Object[] digits;
    private HttpStatus httpStatus;

    protected AppSqlException(BasicSpecificationException error) {
        this.error = error;
    }

    public AppSqlException doJob(SQLException exception) {
       return null;
    }

    public AppSqlException(BasicSpecificationException error,HttpStatus httpStatus) {
        this.error = error;
        this.httpStatus = httpStatus;
        this.digits = digits;
    }


}
