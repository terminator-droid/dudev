package com.dudev.basetest;

import com.dudev.dao.RepositoryBase;
import com.dudev.entity.BaseEntity;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.ClassOrderer;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.manipulation.Orderer;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@RequiredArgsConstructor
public abstract class RepositoryTestBase<T extends Serializable, E extends BaseEntity<T>> extends TransactionManagementTestBase {

    private final RepositoryBase<T, E> repository;
    private final E entity;

    @Order(1)
    @Test
    void save() {
        repository.save(entity);

        assertThat(entity.getId()).isNotNull();
    }

    @Order(2)
    @Test
    void findById() {
        repository.save(entity);
        session.clear();

        Optional<E> actualEntity = repository.findById(entity.getId());

        assertThat(actualEntity).isPresent();
        assertThat(actualEntity.get()).isEqualTo(entity);
    }

    @Order(3)
    @Test
    void delete() {
        repository.save(entity);
        session.clear();

        repository.delete(entity);

        assertThat(repository.findById(entity.getId())).isNotPresent();
    }

    @Order(4)
    @Test
    void update() {
        repository.save(entity);
        session.clear();

        LocalDateTime createdAt = LocalDateTime.of(2000, 1, 1, 2, 2, 1);
        entity.setCreatedAt(createdAt);
        repository.update(entity);
        session.clear();

        assertThat(repository.findById(entity.getId()).get()).isEqualTo(entity);
    }

//    @Test
//    void findAll() {
//        List<E> actualResult = repository.findAll();
//
//        assertThat(actualResult.size()).isEqualTo(entities.size());
//    }
}
