package com.example.demo.core.service;

import com.example.demo.core.entity.BaseEntity;
import com.example.demo.core.repository.BaseRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;

@Transactional
public class BaseServiceImpl<E extends BaseEntity<ID>, ID, R extends BaseRepository<E, ID>> extends BaseEntityManager implements BaseService<E, ID> {

    protected R repository;
    private Class<E> entityClass;

    private BaseServiceImpl(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    public BaseServiceImpl(R repository) {
        this.repository = repository;
    }

    public BaseServiceImpl(R repository, Class<E> entityClass) {
        this.repository = repository;
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
