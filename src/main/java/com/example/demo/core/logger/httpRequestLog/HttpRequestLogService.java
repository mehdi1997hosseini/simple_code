package com.example.demo.core.logger.httpRequestLog;

import com.example.demo.core.logger.httpRequestLog.dto.HttpRequestLogDto;
import com.example.demo.core.service.BasicService;

public interface HttpRequestLogService extends BasicService<HttpRequestLogEntity,String> {
    void saveOrUpdate(HttpRequestLogDto httpRequestLogDto);
}
