package com.example.demo.core.exceptionHandler;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class BasicResponseException {

    private String message;
    private String code;
    private String detailMessage;
    private LocalDateTime localDateTime;
    private String instanceURI;

    private BasicResponseException() {
        this.localDateTime = LocalDateTime.now();
    }

    public BasicResponseException(String message, String code) {
        this();
        this.message = message;
        this.code = code;
    }

    public BasicResponseException(String message, String code, String detailMessage) {
        this();
        this.message = message;
        this.code = code;
        this.detailMessage = detailMessage;
    }
    public BasicResponseException(String message, String code, String detailMessage,String instanceURI) {
        this();
        this.message = message;
        this.code = code;
        this.detailMessage = detailMessage;
        this.instanceURI = instanceURI;
    }
}
