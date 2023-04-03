package com.dudev.dao;

import com.dudev.entity.Pedal;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class PedalRepository extends RepositoryBase<Integer, Pedal> {

    public PedalRepository(EntityManager entityManager) {
        super(entityManager, Pedal.class);
    }
}
