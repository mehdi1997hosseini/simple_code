package com.example.demo.core.service;

import com.example.demo.core.entity.BasicEntity;

public interface BasicService<E extends BasicEntity<ID>, ID> {
    void save(E entity);
    E findById(ID id);
    Boolean softDeleteById(ID id);
}
