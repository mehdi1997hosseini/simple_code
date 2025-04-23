package com.example.demo.core.controller;

import com.example.demo.core.entity.BasicEntity;
import com.example.demo.core.service.BasicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;

@Validated
@Slf4j
public abstract class BasicController<E extends BasicEntity<ID>, ID, S extends BasicService<E, ID>> {

    protected S service;

    public BasicController(S service) {
        this.service = service;
    }

}
