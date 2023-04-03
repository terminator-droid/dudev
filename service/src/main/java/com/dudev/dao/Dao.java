package com.dudev.dao;

import com.dudev.entity.BaseEntity;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.annotation.Inherited;
import java.util.List;
import java.util.Optional;

public interface Dao<K extends Serializable, E extends BaseEntity<K>> {

    E save(E entity);

    void delete(E entity);

    void update(E entity);

    Optional<E> findById(K id);

    List<E> findAll();
}
