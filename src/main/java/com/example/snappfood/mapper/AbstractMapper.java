package com.example.snappfood.mapper;

import com.example.snappfood.entity.AbstractEntity;
import com.example.snappfood.models.AbstractModel;

public interface AbstractMapper<T extends AbstractEntity, R extends AbstractModel<T>> {

    T toEntity(R model);
    R toModel(T entity);

}