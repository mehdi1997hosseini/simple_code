package com.example.demo.core.service;

import com.example.demo.core.entity.BasicEntity;
import com.example.demo.core.repository.BasicRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public non-sealed class BasicServiceImpl<E extends BasicEntity<ID>, ID, R extends BasicRepository<E, ID>> extends BasicEntityManager implements BasicService<E, ID> {
    protected Logger logger;
    protected R repository;
    private Class<E> entityClass;

    public BasicServiceImpl(R repository) {
        this.repository = repository;
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    public BasicServiceImpl(R repository, Class<E> entityClass) {
        this(repository);
        this.entityClass = entityClass;
    }

    @Override
    public void save(E entity) {
        repository.save(entity);
    }

    @Override
    public E findById(ID id) {
        return null;
    }

    @Override
    public Boolean softDeleteById(ID id) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaUpdate<E> update = cb.createCriteriaUpdate(entityClass);
        Root<E> root = update.from(entityClass);
        update.set("isDelete", Boolean.TRUE);
        update.where(cb.equal(root.get("id"), id));
        return getEntityManager().createQuery(update).executeUpdate() > 0;
    }

}
