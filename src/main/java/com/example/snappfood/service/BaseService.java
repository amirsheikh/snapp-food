package com.example.snappfood.service;

import com.example.snappfood.models.AbstractModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BaseService<T extends AbstractModel<?>> {

    Page<T> findAll(Pageable pageable);
    Optional<T> findOne(String uid);
    T update(T model);
    void delete(String uid);

}