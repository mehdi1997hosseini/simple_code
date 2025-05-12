package com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.cache.authConfigCache;

import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService.ExternalOrganizationAuthServiceEntity;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.enums.ExternalOrganizationName;

import java.util.List;
import java.util.Map;

/**
 * =============================================================================================
 * External Organization Cache Service
 * سرویس کش توکن سازمان‌های بیرونی
 * =============================================================================================
 *
 * @Description :
 * This interface is responsible for managing the in-memory cache layer for external organization tokens.
 * It enables fast access, synchronization, and control over token data used in integration scenarios.
 * <p>
 * این اینترفیس مسئول مدیریت لایه کش حافظه‌ای (Cache) برای توکن‌های سازمان‌های بیرونی است.
 * با استفاده از اینترفیس، دسترسی سریع، هماهنگی، و کنترل مؤثر روی اطلاعات توکن‌های مرتبط با سیستم‌های بیرونی
 * امکان‌پذیر می‌گردد.
 * <p>
 * ⚙️ کاربرد:
 * زمانی که بخواهیم اطلاعات توکن‌ها برای سازمان‌های بیرونی به‌صورت لحظه‌ای در حافظه نگه داشته شوند (برای جلوگیری از
 * دسترسی مکرر به دیتابیس یا منابع کند خارجی)، از این سرویس استفاده می‌کنیم.
 * <p>
 * Typical Use-Cases:
 * - Reducing DB load by caching token data
 * - Synchronizing token info on-demand
 * - Refreshing or evicting token data for external systems
 */
public interface ExternalOrganizationAuthCatchService {
    Boolean saveOrUpdate(ExternalOrganizationAuthServiceEntity externalOrganizationAuthServiceEntity);

    Boolean refreshExternalOrganizationByEntity(ExternalOrganizationAuthServiceEntity externalOrganizationAuthServiceEntity);

    Boolean removeExternalOrganizationFromCatch(ExternalOrganizationAuthServiceEntity externalOrganizationAuthServiceEntity);

    void refreshAllCatch(List<ExternalOrganizationAuthServiceEntity> findAllExtOrg);


    public Boolean isExternalOrganizationExist(ExternalOrganizationName externalOrganizationName);

    public Map<ExternalOrganizationName, ExternalOrganizationAuthServiceEntity> findAllExternalOrganization();

    public ExternalOrganizationAuthServiceEntity findExternalOrganizationByOrgName(ExternalOrganizationName organizationName);

}
