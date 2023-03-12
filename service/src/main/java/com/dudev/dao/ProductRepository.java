package com.dudev.dao;

import com.dudev.entity.Product;

import javax.persistence.EntityManager;

public class ProductRepository extends RepositoryBase<Integer, Product> {

    public ProductRepository(EntityManager entityManager, Class<Product> clazz) {
        super(entityManager, clazz);
    }
}
