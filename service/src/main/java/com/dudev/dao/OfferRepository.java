package com.dudev.dao;

import com.dudev.entity.Offer;

import javax.persistence.EntityManager;

public class OfferRepository extends RepositoryBase<Integer, Offer> {

    public OfferRepository(EntityManager entityManager, Class<Offer> clazz) {
        super(entityManager, clazz);
    }
}
