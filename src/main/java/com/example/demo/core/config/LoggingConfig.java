package com.example.demo.core.config;


import com.example.demo.core.exceptionHandler.BasicAppExceptionType;
import com.example.demo.core.exceptionHandler.exception.AppRunTimeException;
import com.example.demo.core.logger.annotation.LoggableRequestResponseApiInDB;
import com.example.demo.core.logger.httpRequestLog.HttpRequestLogService;
import com.example.demo.core.logger.httpRequestLog.StatusRequestType;
import com.example.demo.core.logger.httpRequestLog.dto.HttpRequestLogDto;
import com.example.demo.core.service.BasicService;
import com.example.demo.core.utility.NumberUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

@Slf4j
@Aspect
public class LoggingConfig implements Filter, HandlerInterceptor {

    protected final HttpRequestLogService httpRequestLogService;

    public LoggingConfig(HttpRequestLogService httpRequestLogService) {
        this.httpRequestLogService = httpRequestLogService;
    }

    public LoggingConfig() {
        this(null);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        MDC.put("requestId", generateRequestId());
        log.info("Filter  -----> LoggingConfig init based on request id {}", MDC.get("requestId"));
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        String requestId = MDC.get("requestId");
        MDC.remove("requestId");
        log.info("Filter  -----> LoggingConfig destroy based on request id {}", requestId);
        Filter.super.destroy();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        String methodURI = request.getMethod();
        log.info("INTERCEPTOR -----> Incoming request to method: {} , request URI: {} and request ID : {}", methodURI, requestURI, MDC.get("requestId"));
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("INTERCEPTOR -----> Incoming request completed by request id : {} - and response status : {} ", MDC.get("requestId"), response.getStatus());
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    // Around advice برای لاگ‌گذاری همزمان درخواست و پاسخ
    @Around("@annotation(loggableRequestResponseApiInDB) && (@annotation(org.springframework.web.bind.annotation.PostMapping) || @annotation(org.springframework.web.bind.annotation.GetMapping))")
    public Object logRequestResponse(ProceedingJoinPoint joinPoint, LoggableRequestResponseApiInDB loggableRequestResponseApiInDB) throws Throwable {
        String requestId = MDC.get("requestId");
        // اطلاعات درخواست
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String requestURI = request.getRequestURI();
        String method = request.getMethod();

        Object[] args = joinPoint.getArgs();

        long start = System.currentTimeMillis();

        log.info("AOP -----> Incoming request to method: {} with args: {} and request Id : {} ", joinPoint.getSignature(), args, requestId);
        HttpRequestLogDto httpRequestLog = HttpRequestLogDto.builder()
                .url(requestURI)
                .method(method)
                .build();

        Object result = null;
        try {
            Object responseBody;
            Object headers;
            result = joinPoint.proceed();
            if (result instanceof ResponseEntity<?> response) {
                responseBody = response.getBody();
                headers = response.getHeaders();
            } else if (result instanceof HttpEntity<?> httpEntity) {
                responseBody = httpEntity.getBody();
                headers = httpEntity.getHeaders();
            } else {
                responseBody = result;
                headers = result;
            }

            long duration = System.currentTimeMillis() - start;
            httpRequestLog.setResponseTime((int) duration);

            httpRequestLog.setHeaders(headers.toString());
            httpRequestLog.setResponseBody(responseBody != null ? responseBody.toString() : "");
            log.info("AOP -----> Response from method: {}  - by request id : {}", result, requestId);
            httpRequestLog.setStatus(StatusRequestType.ACCEPT);
        } catch (Exception e) {
            log.error("AOP -----> Error occurred while processing request: {} {} - request id : {}", method, requestURI, requestId);
            httpRequestLog.setStatus(StatusRequestType.FAILED);
            long duration = System.currentTimeMillis() - start;
            httpRequestLog.setResponseTime((int) duration);
            saveIfAllowed(httpRequestLog);
            throw new AppRunTimeException(BasicAppExceptionType.BAD_REQUEST, e.getMessage());
        }

        saveIfAllowed(httpRequestLog);

        return result;
    }

    // پس از اجرای متد، لاگ‌گذاری برای مقادیر برگشتی
    @AfterReturning(pointcut = "@within(org.springframework.stereotype.Service)|| @within(org.springframework.stereotype.Component)", returning = "result")
    public void logAfterReturn(JoinPoint joinPoint, Object result) {
        String requestId = MDC.get("requestId");
        String methodName = joinPoint.getSignature().getName();

        if (result instanceof Throwable) {
            log.error("request id : {} - return exception on Method : {} - threw an exception: {} ", requestId, methodName, result);
        } else {
            log.info("request id : {} - return on Method {} - executed successfully with result: {} ", requestId, methodName, result);
        }
    }


    // پس از اجرای متد، اگر خطا در متد رخ دهد، لاگ‌گذاری خطا
    @After("@within(org.springframework.stereotype.Service)|| @within(org.springframework.stereotype.Component)")
    public void logAfterException(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        // اگر خطایی در حین اجرای متد رخ دهد
        Throwable throwable = getThrowable(joinPoint);
        if (throwable != null) {
            log.error("request id : {} - Exception in Method {} failed with error: {}", MDC.get("requestId"), methodName, throwable.getMessage());
        }
    }

    // گرفتن اطلاعات مربوط به خطا
    private Throwable getThrowable(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof Throwable) {
                return (Throwable) arg;
            }
        }
        return null;
    }

    private String generateRequestId() {
        return String.valueOf(NumberUtils.uniqueNumber());
    }

    private void saveIfAllowed(HttpRequestLogDto logDto) {
        if (httpRequestLogService != null) {
            if (httpRequestLogService instanceof BasicService<?, ?>) {
                // اگر از BasicService ارث برده بود
                httpRequestLogService.saveOrUpdate(logDto);
            } else {
                // اگر نبود فقط در فایل لاگ زده شود (که بالا انجام شد)
                log.info("HttpRequestLogDto not saved to DB, service not instance of BasicService.");
            }
        } else {
            log.info("HttpRequestLogService is null. Saving only to file logs.");
        }
    }
}
