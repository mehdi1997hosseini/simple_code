package com.example.demo.core.logger.httpRequestLog;

import com.example.demo.core.logger.httpRequestLog.dto.HttpRequestLogDto;
import com.example.demo.core.mapper.BasicMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HttpRequestLogMapper extends BasicMapper<HttpRequestLogEntity, HttpRequestLogDto> {

}
