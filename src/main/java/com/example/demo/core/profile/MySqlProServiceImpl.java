package com.example.demo.core.profile;

import com.example.demo.core.exceptionHandler.BasicAppExceptionType;
import com.example.demo.core.exceptionHandler.exception.AppRunTimeException;
import com.example.demo.core.logger.annotation.LoggableRequestResponseApi;
import com.example.demo.core.logger.httpRequestLog.HttpRequestLogService;
import com.example.demo.core.logger.httpRequestLog.StatusRequestType;
import com.example.demo.core.logger.httpRequestLog.dto.HttpRequestLogDto;
import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationService;
import com.example.demo.core.thirdParty.externalOrganization.cache.ExternalOrganizationCatchService;
import com.example.demo.core.thirdParty.externalOrganization.token.TokenSchedulerService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Optional;

@Service
@Profile("mysql")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Aspect
public class MySqlProServiceImpl implements ProfilesService, CommandLineRunner, HandlerInterceptor {

    private final ExternalOrganizationService externalOrganizationService;
    private final ExternalOrganizationCatchService externalOrganizationCatchService;
    private final TokenSchedulerService tokenSchedulerService;
    private final HttpRequestLogService httpRequestLogService;

    private static final Logger logger = LoggerFactory.getLogger(MySqlProServiceImpl.class);

    @Override
    public String getProfileName() {
        return "profile By Database MySql ....";
    }

    @Override
    public void run(String... args) throws Exception {
        tokenProcess();
    }

    private void tokenProcess() {
        externalOrganizationCatchService.refreshAllCatch(externalOrganizationService.findAllInCache());
        Optional.ofNullable(externalOrganizationCatchService.findAllExternalOrganization())
                .filter(map -> !map.isEmpty())
                .ifPresent(tokenSchedulerService::initTokens);
    }

    // Around advice برای لاگ‌گذاری همزمان درخواست و پاسخ
    @Around("@annotation(loggableRequestResponseApi) && @annotation(org.springframework.web.bind.annotation.PostMapping)")
    public Object logRequestResponse(ProceedingJoinPoint joinPoint, LoggableRequestResponseApi loggableRequestResponseApi) throws Throwable {
        // اطلاعات درخواست
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String requestURI = request.getRequestURI();
        String method = request.getMethod();

        Object[] args = joinPoint.getArgs();

        logger.info("Incoming request to method: {} with args: {}", joinPoint.getSignature(), args);
        HttpRequestLogDto httpRequestLog = HttpRequestLogDto.builder()
                .url(requestURI)
                .method(method)
                .build();
//.headers(request.getHeader("username"))
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
            httpRequestLog.setHeaders(headers.toString());
            httpRequestLog.setResponseBody(responseBody != null ? responseBody.toString() : "");
            logger.info("Response from method: {}", result);
            httpRequestLog.setStatus(StatusRequestType.ACCEPT);
        } catch (Exception e) {
            logger.error("Error occurred while processing request: {} {}", method, requestURI);
            httpRequestLog.setStatus(StatusRequestType.FAILED);
            httpRequestLogService.saveOrUpdate(httpRequestLog);
            throw new AppRunTimeException(BasicAppExceptionType.BAD_REQUEST, e.getMessage());
        }

        httpRequestLogService.saveOrUpdate(httpRequestLog);

        return result;
    }


}