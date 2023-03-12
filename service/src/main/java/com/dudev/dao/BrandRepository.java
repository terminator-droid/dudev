package com.dudev.dao;

import com.dudev.entity.Brand;
import com.dudev.entity.User;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManager;

public class BrandRepository extends RepositoryBase<Integer, Brand>{

    public BrandRepository(EntityManager entityManager, Class<Brand> clazz) {
        super(entityManager, clazz);
    }
}
