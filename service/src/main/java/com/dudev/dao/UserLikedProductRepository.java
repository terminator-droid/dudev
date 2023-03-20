package com.dudev.dao;

import com.dudev.entity.UserLikedProduct;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class UserLikedProductRepository extends RepositoryBase<Integer, UserLikedProduct> {

    public UserLikedProductRepository(EntityManager entityManager) {
        super(entityManager, UserLikedProduct.class);
    }
}
