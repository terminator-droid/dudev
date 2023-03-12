package com.dudev.dao;

import com.dudev.entity.Pedal;

import javax.persistence.EntityManager;

public class PedalRepository extends RepositoryBase<Integer, Pedal> {

    public PedalRepository(EntityManager entityManager, Class<Pedal> clazz) {
        super(entityManager, clazz);
    }
}
