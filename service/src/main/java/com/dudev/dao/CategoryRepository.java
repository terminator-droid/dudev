package com.dudev.dao;

import com.dudev.entity.Category;

import javax.persistence.EntityManager;

public class CategoryRepository extends RepositoryBase<Integer, Category> {

    public CategoryRepository(EntityManager entityManager, Class<Category> clazz) {
        super(entityManager, clazz);
    }
}
