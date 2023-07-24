package com.example.snappfood.service;


import com.example.snappfood.entity.AbstractEntity;
import com.example.snappfood.mapper.AbstractMapper;
import com.example.snappfood.models.AbstractModel;
import com.example.snappfood.repository.AbstractEntityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

public abstract class AbstractService<T extends AbstractEntity, R extends AbstractModel<T>> implements BaseService<R> {

    abstract AbstractEntityRepository<T> getRepository();
    abstract AbstractMapper<T, R> getMapper();

    @Override
    @Transactional(readOnly = true)
    public Page<R> findAll(Pageable pageable) {
        return getRepository().findAll(pageable)
                .map(this::findAllMapper);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<R> findOne(String uid) {
        return getRepository().findByExtuid(uid)
                .map(this::findOneMapper);
    }

    @Override
    @Transactional
    public void delete(String uid) {
        getRepository().deleteByExtuid(uid);
    }

    public T getEntity(R model) throws NoSuchElementException {
        return getRepository().findByExtuid(model.getExtuid())
                .orElseThrow();
    }

    R findAllMapper(T entity) {
        return getMapper().toModel(entity);
    }

    R findOneMapper(T entity) {
        return getMapper().toModel(entity);
    }

}

