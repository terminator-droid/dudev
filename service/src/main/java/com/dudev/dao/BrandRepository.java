package com.dudev.dao;

import com.dudev.entity.Brand;
import com.dudev.entity.User;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class BrandRepository extends RepositoryBase<Integer, Brand> {

    public BrandRepository(EntityManager entityManager) {
        super(entityManager, Brand.class);
    }
}
