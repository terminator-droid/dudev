package com.dudev.dao;

import com.dudev.entity.ChangeType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class ChangeTypeRepository extends RepositoryBase<Integer, ChangeType> {

    public ChangeTypeRepository(EntityManager entityManager) {
        super(entityManager, ChangeType.class);
    }
}
