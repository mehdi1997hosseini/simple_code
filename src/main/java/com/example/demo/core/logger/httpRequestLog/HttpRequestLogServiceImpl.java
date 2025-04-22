package com.example.demo.core.logger.httpRequestLog;

import com.example.demo.core.logger.httpRequestLog.dto.HttpRequestLogDto;
import com.example.demo.core.service.BasicServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class HttpRequestLogServiceImpl extends BasicServiceImpl<HttpRequestLogEntity, String, HttpRequestLogRepository> implements HttpRequestLogService {
    private final HttpRequestLogMapper httpRequestLogMapper;
    public HttpRequestLogServiceImpl(HttpRequestLogRepository repository, Class<HttpRequestLogEntity> entityClass, HttpRequestLogMapper httpRequestLogMapper) {
        super(repository, entityClass);
        this.httpRequestLogMapper = httpRequestLogMapper;
    }

    @Override
    public void saveOrUpdate(HttpRequestLogDto httpRequestLogDto) {
        HttpRequestLogEntity entity = httpRequestLogMapper.toEntity(httpRequestLogDto);
        save(entity);
    }
}
