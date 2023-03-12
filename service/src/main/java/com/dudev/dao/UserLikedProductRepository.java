package com.dudev.dao;

import com.dudev.entity.UserLikedProduct;

import javax.persistence.EntityManager;

public class UserLikedProductRepository extends RepositoryBase<Integer, UserLikedProduct> {

    public UserLikedProductRepository(EntityManager entityManager, Class<UserLikedProduct> clazz) {
        super(entityManager, clazz);
    }
}
