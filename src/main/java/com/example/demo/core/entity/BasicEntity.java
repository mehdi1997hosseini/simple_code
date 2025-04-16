package com.example.demo.core.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public class BasicEntity<ID>  implements Serializable {
    @Id
    private ID id;

    @PrePersist
    private void generateIdIfNeeded() {
        if (id == null) {
            Class<?> idType = getIdType();
            if (idType.equals(String.class)) {
                id = (ID) UUID.randomUUID().toString();
            } else if (idType.equals(Long.class)) {
                id = (ID) Long.valueOf(UUID.randomUUID().getMostSignificantBits() ^ UUID.randomUUID().getLeastSignificantBits());
            } else if (idType.equals(Integer.class)) {
                id = (ID) Integer.valueOf((int) UUID.randomUUID().getMostSignificantBits() & 0xFFFFFFFF);
            } else
                throw new IllegalArgumentException("type of primary key is not valid ... checking the primary key...");
        }
    }

    private Class<?> getIdType() {
        Type superclass = getClass().getGenericSuperclass();
        if (superclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) superclass;
            Type[] typeArguments = parameterizedType.getActualTypeArguments();
            if (typeArguments.length > 0) {
                return (Class<?>) typeArguments[0];
            }
        }
        return String.class;
    }
}