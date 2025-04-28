package com.example.demo.core.logger.httpRequestLog;

import com.example.demo.core.entity.BasicEntity;
import jakarta.persistence.*;
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
    private String uri;
    private String method;
    private String headers;

    @Column(name = "RESPONSE_TIME")
    private Integer responseTime;

    @Column(name = "REQUEST_BODY", length = 1000)
    @Lob
    private String requestBody;
    @Column(name = "RESPONSE_BODY", length = 1000)
    @Lob
    private String responseBody;
    @Enumerated(EnumType.STRING)
    private StatusRequestType status;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
