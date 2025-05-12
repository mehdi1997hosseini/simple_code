package com.example.demo.core.service;

import com.example.demo.core.entity.BasicEntity;
import com.example.demo.core.repository.BasicRepository;

import java.util.List;

/**
 * =============================================================================================
 * Basic Infrastructure Service Implementation
 * پیاده‌سازی پایه‌ای سرویس زیرساخت برای عملیات مرتبط با Entity
 * =============================================================================================
 *
 * @Description :
 * This class provides a generic and reusable implementation of {@link BasicInfrastructureService}
 * for handling basic data operations (CRUD) on entities using a Spring Data repository.
 *
 * این کلاس یک پیاده‌سازی عمومی و قابل استفاده مجدد از {@link BasicInfrastructureService} برای
 * انجام عملیات پایه‌ای دیتایی (CRUD) بر روی موجودیت‌ها با استفاده از Repositoryهای Spring Data ارائه می‌دهد.
 *
 * Generic Parameters:
 * پارامترهای جنریک:
 *
 * @param <E> The entity type (must extend BasicEntity<ID>)
 *            نوع موجودیت که باید از BasicEntity ارث‌بری کند
 * @param <ID> The type of the entity's unique identifier
 *             نوع شناسه یکتای موجودیت
 * @param <R> The repository used to access the database
 *            ریپازیتوری‌ای که برای دسترسی به دیتابیس استفاده می‌شود
 */
public non-sealed class BasicInfrastructureServiceImpl<E extends BasicEntity<ID>, ID, R extends BasicRepository<E, ID>> extends BasicEntityManager implements BasicInfrastructureService<E,ID> {
    protected Class<E> entityClass;
    protected R repository;

    public BasicInfrastructureServiceImpl(R repository) {
        this.repository = repository;
    }

    public BasicInfrastructureServiceImpl(Class<E> entityClass, R repository) {
        this(repository);
        this.entityClass = entityClass;
    }

    @Override
    public E save(E entity) {
        return repository.save(entity);
    }

    @Override
    public E findById(ID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<E> findAll() {
        return repository.findAll();
    }

}
