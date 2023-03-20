package com.dudev.dao;

import com.dudev.entity.Offer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class OfferRepository extends RepositoryBase<Integer, Offer> {

    public OfferRepository(EntityManager entityManager) {
        super(entityManager, Offer.class);
    }
}
