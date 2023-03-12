package com.dudev.dao;

import javax.persistence.EntityManager;

public class OfferProduct extends RepositoryBase<Integer, com.dudev.entity.OfferProduct> {

    public OfferProduct(EntityManager entityManager, Class<com.dudev.entity.OfferProduct> clazz) {
        super(entityManager, clazz);
    }
}
