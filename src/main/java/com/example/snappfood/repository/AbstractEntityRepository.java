package com.example.snappfood.repository;

import com.example.snappfood.entity.AbstractEntity;
import com.example.snappfood.models.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface AbstractEntityRepository<T extends AbstractEntity> extends JpaRepository<T, Long>,
        JpaSpecificationExecutor<T> {

    Optional<T> findByExtuid(String extuid);
    void deleteByExtuid(String extuid);

}
