package com.dudev.dao;

import javax.persistence.EntityManager;

public class OfferProductRepository extends RepositoryBase<Integer, com.dudev.entity.OfferProduct> {

    public OfferProductRepository(EntityManager entityManager) {
        super(entityManager, com.dudev.entity.OfferProduct.class);
    }
}
