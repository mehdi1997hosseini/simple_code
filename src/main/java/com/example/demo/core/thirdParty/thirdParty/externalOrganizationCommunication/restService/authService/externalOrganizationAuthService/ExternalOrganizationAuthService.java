package com.example.demo.core.thirdParty.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService;

import ir.smarttrustco.pssnote.core.service.BasicService;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService.dto.ExternalOrganizationAuthServiceDto;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.enums.ExternalOrganizationName;

import java.util.List;

/**
 * =============================================================================================
 * External Organization Auth Service
 * سرویس مدیریت توکن‌های سازمان‌های بیرونی
 * =============================================================================================
 *
 * @Description :
 * This interface provides operations for managing external organizations and their associated access tokens.
 * It extends the BasicService for basic CRUD functionality and adds specialized methods for token lifecycle,
 * caching, and integration control of external systems.
 * این اینترفیس عملیات مربوط به مدیریت سازمان‌های بیرونی و توکن‌های دسترسی مربوط به آن‌ها را ارائه می‌دهد.
 * این اینترفیس از BasicService ارث‌بری می‌کند تا عملیات پایه (CRUD) را شامل شود و همچنین متدهای خاصی برای
 * کنترل چرخه‌ی حیات توکن، کش‌کردن داده‌ها، و تعامل دستی با سیستم‌های بیرونی فراهم می‌سازد.
 *  کاربرد:
 * اینترفیس زمانی کاربرد دارد که سامانه با سازمان‌های بیرونی (مانند بیمه، بانک، سرویس دولتی و...) در تعامل باشد
 * و لازم باشد که اطلاعات دسترسی یا احراز هویت آن‌ها (مانند توکن) را ذخیره، به‌روزرسانی، کش یا قطع موقت کرد.
 *
 */
public interface ExternalOrganizationAuthService extends BasicService<ExternalOrganizationAuthServiceEntity,String> {
    ExternalOrganizationAuthServiceDto saveOrUpdate(ExternalOrganizationAuthServiceDto externalOrganization);
    List<ExternalOrganizationAuthServiceDto> findAll();
    List<ExternalOrganizationAuthServiceEntity> findAllInCacheWhenStartApp();
    List<ExternalOrganizationAuthServiceEntity> findExternalOrganizationByOrgName(ExternalOrganizationName orgName);

    void refreshManuallyExternalOrganization(String organizationName);
    void shotDownManuallyExternalOrganizationForGetToken(String organizationName);
}
