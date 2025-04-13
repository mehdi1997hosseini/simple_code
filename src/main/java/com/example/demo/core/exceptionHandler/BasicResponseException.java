package com.example.demo.core.exceptionHandler;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class BasicResponseException {

    private String message;
    private String code;
    private String detailMessage;
    private LocalDateTime localDateTime;

    @Override
    public String toString() {
        return "{" +
                "message='" + message + '\'' +
                ", code='" + code + '\'' +
                ", detailMessage='" + detailMessage + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
