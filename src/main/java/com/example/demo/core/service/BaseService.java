package com.example.demo.core.service;

import com.example.demo.core.entity.BaseEntity;

public interface BaseService<E extends BaseEntity<ID>, ID> {
    void save(E entity);
    E findById(ID id);
    Boolean softDeleteById(ID id);
}
