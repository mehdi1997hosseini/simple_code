package com.example.demo.core.exceptionHandler;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@Builder
public class BasicResponseException {

    private String message;
    private String code;
    private String detailMessage;
    private LocalDateTime localDateTime;

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
}
