package com.example.demo.core.service;

import com.example.demo.core.entity.BasicEntity;
import com.example.demo.core.mapper.BasicMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * =============================================================================================
 * Basic Implementation of the DTO Service Layer
 * پیاده‌سازی پایه‌ای از لایه سرویس برای اشیای انتقال داده (DTO)
 * =============================================================================================
 *
 * @Description :
 * This class provides a generic implementation of {@link BasicDtoService}, enabling standard
 * CRUD-like operations for DTOs by delegating logic to an infrastructure service and using a mapper.
 *
 * این کلاس یک پیاده‌سازی عمومی از {@link BasicDtoService} فراهم می‌کند و با استفاده از سرویس زیرساختی و مَپِر،
 * عملیات پایه‌ای مانند ذخیره، خواندن و حذف نرم را برای DTOها پیاده‌سازی می‌کند.
 *
 * Generic Parameters:
 * پارامترهای جنریک:
 *
 * @param <E> Entity type (must extend BasicEntity<ID>)
 *            نوع موجودیت (Entity) که باید از BasicEntity ارث‌بری کند
 * @param <ID> Type of the entity's unique identifier
 *             نوع شناسه یکتا
 * @param <D> DTO type
 *            نوع شی انتقال داده (DTO)
 * @param <M> Mapper interface to convert between Entity and DTO
 *             اینترفیس mapper برای تبدیل بین Entity و DTO
 * @param <I> Infrastructure Service Interface for Entity Operations as well as their relationship with database
 *            اینترفیس سرویس زیرساختی برای انجام عملیات مربوط به Entity و همینطور ارتباط انها با دیتابیس
 */

public class BasicDtoServiceImpl<E extends BasicEntity<ID>, ID, D, M extends BasicMapper<E, D>, I extends BasicInfrastructureService<E, ID>> implements BasicDtoService<E, ID, D> {
    protected Logger logger;
    protected I currentInfrastructureService;
    protected M mapper;

    public BasicDtoServiceImpl(I currentInfrastructureService, M mapper) {
        this.currentInfrastructureService = currentInfrastructureService;
        this.mapper = mapper;
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    @Override
    public D save(D dto) {
        return mapper.toDto(currentInfrastructureService.save(mapper.toEntity(dto)));
    }

    @Override
    public List<D> saveList(List<D> listDto) {
        if (listDto == null || listDto.isEmpty())
            return null;

        List<D> dtoList = new ArrayList<D>();
        for (D dto : listDto) {
            dtoList.add(this.save(dto));
        }
        return dtoList;
    }

    @Override
    public D findById(ID id) {
        return mapper.toDto(currentInfrastructureService.findById(id));
    }

    @Override
    public List<D> findAll() {
        return mapper.toDto(currentInfrastructureService.findAll());
    }

    @Override
    public Boolean softDelete(ID id) {
        E entity = currentInfrastructureService.findById(id);
        if (entity == null)
            return false;

        entity.setIsDeleted(true);

        return currentInfrastructureService.save(entity).getId() != null;
    }
}
