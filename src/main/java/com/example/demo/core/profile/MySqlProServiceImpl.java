package com.example.demo.core.profile;

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
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
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

    // نقطه‌برش برای متدهایی که انوتیشن @LoggableRequestResponseApi دارند
    @Pointcut("@annotation(com.example.demo.core.logger.annotation.LoggableRequestResponseApi)")
    public void loggableApiMethods() {}

    // Around advice برای لاگ‌گذاری همزمان درخواست و پاسخ
    @Around("loggableApiMethods() && @annotation(org.springframework.web.bind.annotation.PostMapping)")
    public Object logRequestResponse(ProceedingJoinPoint joinPoint) throws Throwable {
        // اطلاعات درخواست
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String requestURI = request.getRequestURI();
        String method = request.getMethod();



        // گرفتن پارامترهای درخواست
        Object[] args = joinPoint.getArgs();
        // لاگ درخواست
        logger.info("Incoming request to method: {} with args: {}", joinPoint.getSignature(), args);
        HttpRequestLogDto httpRequestLog = HttpRequestLogDto.builder()
                .url(requestURI)
                .method(method)
                .headers(request.getHeader("username")).build();
        // پردازش متد و گرفتن نتیجه (پاسخ)
        // پیشبرد متد
        Object result = null;
        try {
            result = joinPoint.proceed();
            httpRequestLog.setStatus(StatusRequestType.ACCEPT);
            httpRequestLog.setResponseBody(result != null ? result.toString() : "");
            // لاگ پاسخ
            logger.info("Response from method: {}", result);

        } catch (Exception e) {
            // در صورت بروز خطا لاگ کنیم
            logger.error("Error occurred while processing request: {} {}", method, requestURI, e);
            httpRequestLog.setStatus(StatusRequestType.FAILED);
            throw new RuntimeException(e.getMessage());  // پرتاب مجدد استثنا برای مدیریت در جاهای دیگر
        }
        // ذخیره‌سازی در دیتابیس
        httpRequestLogService.saveOrUpdate(httpRequestLog);

        // بازگشت نتیجه به متد اصلی
        return result;
    }


}