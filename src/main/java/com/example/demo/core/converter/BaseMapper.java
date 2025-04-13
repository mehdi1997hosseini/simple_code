package com.example.demo.core.converter;

import java.util.List;

/**
 * E is Entity
 * D is DTO class
 * for use from library mapstruct
 * */
public interface BaseMapper<E, D> {
    E toEntity(D d);
    List<E> toEntity(List<D> ds);
    D toDto(E e);
    List<D> toDto(List<E> es);
}
