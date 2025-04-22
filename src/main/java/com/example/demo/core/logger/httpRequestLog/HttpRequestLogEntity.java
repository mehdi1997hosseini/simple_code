package com.example.demo.core.logger.httpRequestLog;

import com.example.demo.core.entity.BasicEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_HTTP_REQ_LOG")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HttpRequestLogEntity extends BasicEntity<String> {
    private String url;
    private String method;
    private String headers;

    private Integer responseTime;

    private String requestBody;
    private String responseBody;
    @Enumerated(EnumType.STRING)
    private StatusRequestType status;
    @CreationTimestamp
    private LocalDateTime timestamp;
}
