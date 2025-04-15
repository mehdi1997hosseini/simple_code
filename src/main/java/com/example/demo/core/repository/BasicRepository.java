package com.example.demo.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BasicRepository<E, ID> extends CrudRepository<E, ID> , JpaRepository<E, ID> {
}
