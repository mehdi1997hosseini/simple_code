package com.example.demo.core.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PersistenceContext;

@MappedSuperclass
public sealed class BasicEntityManager permits BasicServiceImpl , BasicInfrastructureServiceImpl {

    @PersistenceContext
    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        return entityManager;
    }

}
