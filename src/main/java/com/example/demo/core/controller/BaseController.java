package com.example.demo.core.controller;

import com.example.demo.core.entity.BaseEntity;
import com.example.demo.core.service.BaseService;
import org.springframework.validation.annotation.Validated;

@Validated
public abstract class BaseController<E extends BaseEntity<ID>, ID, S extends BaseService<E, ID>> {

    protected S service;

    public BaseController(S service) {
        this.service = service;
    }

}
