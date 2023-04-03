package com.dudev.dao;

import com.dudev.entity.Category;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class CategoryRepository extends RepositoryBase<Integer, Category> {

    public CategoryRepository(EntityManager entityManager) {
        super(entityManager, Category.class);
    }
}
