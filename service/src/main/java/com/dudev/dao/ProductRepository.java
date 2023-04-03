package com.dudev.dao;

import com.dudev.entity.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class ProductRepository extends RepositoryBase<Integer, Product> {

    public ProductRepository(EntityManager entityManager) {
        super(entityManager, Product.class);
    }
}
