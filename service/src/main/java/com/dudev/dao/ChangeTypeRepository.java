package com.dudev.dao;

import com.dudev.entity.ChangeType;

import javax.persistence.EntityManager;

public class ChangeTypeRepository extends RepositoryBase<Integer, ChangeType> {

    public ChangeTypeRepository(EntityManager entityManager) {
        super(entityManager, ChangeType.class);
    }
}
