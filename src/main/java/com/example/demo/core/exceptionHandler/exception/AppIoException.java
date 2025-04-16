package com.example.demo.core.exceptionHandler.exception;

import com.example.demo.core.exceptionHandler.BasicSpecificationException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.IOException;

@Getter
public class AppIoException extends IOException {
    private final BasicSpecificationException error;
    private Object[] digits;
    private HttpStatus httpStatus;

    protected AppIoException(BasicSpecificationException error) {
        super(error.getMessage());
        this.error = error;
    }

}
