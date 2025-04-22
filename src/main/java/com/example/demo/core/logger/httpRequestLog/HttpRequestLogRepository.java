package com.example.demo.core.logger.httpRequestLog;

import com.example.demo.core.repository.BasicRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HttpRequestLogRepository extends BasicRepository<HttpRequestLogEntity, String> {
}
