package com.example.demo.core.controller;

import com.example.demo.core.entity.BasicEntity;
import com.example.demo.core.service.BasicDtoService;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BasicDtoController<E extends BasicEntity<ID>, ID, D, S extends BasicDtoService<E, ID, D>> {

    protected S service;

    public BasicDtoController(S service) {
        this.service = service;
    }


}
