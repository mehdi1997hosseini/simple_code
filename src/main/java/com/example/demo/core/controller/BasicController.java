package com.example.demo.core.controller;

import com.example.demo.core.entity.BasicEntity;
import com.example.demo.core.service.BasicService;
import org.springframework.validation.annotation.Validated;

@Validated
public abstract class BasicController<E extends BasicEntity<ID>, ID, S extends BasicService<E, ID>> {

    protected S service;

    public BasicController(S service) {
        this.service = service;
    }

}
