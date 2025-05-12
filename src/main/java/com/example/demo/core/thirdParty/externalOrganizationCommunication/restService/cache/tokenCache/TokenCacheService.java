package com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.cache.tokenCache;

import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService.token.ExternalTokenDto;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.enums.ExternalOrganizationName;

import java.util.Map;

/**
 * =============================================================================================
 * Token Cache Service
 * سرویس کش توکن‌های سازمان‌های بیرونی
 * =============================================================================================
 *
 * @Description :
 * <p> En : This interface defines the contract for managing the in-memory token cache
 * of external organizations. It provides functionality to store, retrieve, clear,
 * and list all tokens used in external integrations.
 * </p>
 * <p> Fa : این اینترفیس مسئول تعریف عملیات اصلی مربوط به کش (Cache) توکن‌های سازمان‌های بیرونی است.
 * از این سرویس برای ذخیره‌سازی، بازیابی، حذف و مدیریت کامل توکن‌های موجود در حافظه موقت
 * استفاده می‌شود که در تعاملات بین‌سیستمی کاربرد دارد.
 * <p>
 * کاربردهای معمول (Use-Cases):
 * - کاهش بار دسترسی به دیتابیس با نگهداری توکن‌ها در حافظه
 * - پاسخ‌دهی سریع به درخواست‌هایی که نیاز به توکن دارند
 * - پاکسازی و ریست کش در زمان تغییرات سیستمی یا امنیتی
 */
public interface TokenCacheService {
    public void saveOrUpdateToken(ExternalOrganizationName orgName, ExternalTokenDto tokenInfo);

    public ExternalTokenDto getToken(ExternalOrganizationName orgName);

    public void clearDataTokens();

    public Map<ExternalOrganizationName, ExternalTokenDto> getAll();
}
