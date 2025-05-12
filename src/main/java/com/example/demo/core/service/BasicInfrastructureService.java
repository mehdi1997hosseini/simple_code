package com.example.demo.core.service;

import java.util.List;

/**
 * =============================================================================================
 * Basic Infrastructure Service
 *  سرویس پایه لایه زیرساخت در معماری لایه‌ای
 * =============================================================================================
 *
 * @Description :
 * This interface defines the essential contract for the Infrastructure Layer in a layered or hexagonal architecture.
 * It encapsulates the data-access logic, typically implemented using persistence technologies such as JPA, JDBC, etc.
 *
 * این اینترفیس قرارداد پایه‌ای برای لایه زیرساخت (Infrastructure Layer) در معماری لایه‌ای یا شش‌ضلعی را تعریف می‌کند.
 * این لایه وظیفه‌ی پیاده‌سازی عملیات دسترسی به داده را دارد و معمولاً با استفاده از تکنولوژی‌هایی مانند JPA، JDBC و... پیاده‌سازی می‌شود.
 *
 * ⚠️ Note:
 * This interface is used **only in infrastructure layer services** that operate directly on Entity objects.
 * It must NOT contain or interact with DTOs. Services with DTO logic should extend a separate service layer (e.g., BasicDtoService).
 *
 * ⚠️ توجه:
 * این اینترفیس **تنها باید در سرویس‌های لایه زیرساخت** مورد استفاده قرار گیرد که مستقیماً با Entityها کار می‌کنند.
 * اینترفیس نباید شامل هیچ‌گونه منطق مربوط به DTO یا نگاشت (mapping) باشد. برای این اهداف باید از لایه‌های سرویس متفاوت (مثل BasicDtoService) استفاده شود.
 *
 * Generic Parameters:
 * پارامترهای جنریک:
 *
 * @param <E>  Entity type / نوع موجودیت
 * @param <ID> Identifier type / نوع شناسه یکتا
 */
public interface BasicInfrastructureService<E,ID> {
    E save(E entity);
    E findById(ID id);
    List<E> findAll();

}
