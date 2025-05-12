package com.example.demo.core.service;

import com.example.demo.core.entity.BasicEntity;

import java.util.List;

/**
 * =======================================================================
 * Base Interface for DTO Service Layer
 * لایه‌ی پایه‌ای سرویس برای مدیریت عملیات DTOها
 * =======================================================================
 *
 * @Description :
 * This interface defines the basic CRUD-like operations for Data Transfer Objects (DTOs)
 * used in the application service layer.
 * این واسط، عملیات پایه‌ای مشابه CRUD را برای اشیای انتقال داده (DTO) در لایه‌ی سرویس تعریف می‌کند.
 *
 * Generic Parameters:
 * پارامترهای جنریک:
 *
 * @param <E> The entity type which must extend BasicEntity<ID>
 *            نوع موجودیت (Entity) که باید از BasicEntity با شناسه‌ای از نوع ID ارث‌بری کند.
 * @param <ID> The type of the unique identifier (e.g., Long, UUID, etc.)
 *             نوع شناسه یکتا (مانند Long، UUID و غیره)
 * @param <D> The Data Transfer Object (DTO) type
 *            نوع شی انتقال داده (DTO)
 *
 * Typical use:
 * استفاده معمول:
 * Define a DTO service for any entity by extending this interface
 * با گسترش این واسط می‌توان برای هر موجودیتی یک سرویس پایه‌ای برای DTO ایجاد کرد.
 */
public interface BasicDtoService <E extends BasicEntity<ID>, ID , D>{
    D save(D dto);
    List<D> saveList(List<D> listDto);

    D findById(ID id);
    List<D> findAll();

    Boolean softDelete(ID id);

}
