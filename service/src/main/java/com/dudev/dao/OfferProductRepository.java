package com.dudev.dao;

import com.dudev.entity.OfferProduct;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class OfferProductRepository extends RepositoryBase<Integer, OfferProduct> {

    public OfferProductRepository(EntityManager entityManager) {
        super(entityManager, OfferProduct.class);
    }
}
