package com.example.demo.core.logger.httpRequestLog.dto;

import com.example.demo.core.logger.httpRequestLog.StatusRequestType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
@Builder
public class HttpRequestLogDto implements Serializable {
    private String url;
    private String method;
    private String headers;
    private Integer responseTime;
    private String requestBody;
    private String responseBody;
    private StatusRequestType status;

}
