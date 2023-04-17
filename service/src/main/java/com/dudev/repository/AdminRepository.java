package com.dudev.repository;

import com.dudev.util.EntityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class AdminRepository {

    @Autowired
    private static EntityManager entityManager;

    static {
        EntityUtil.insertEntities(entityManager);
    }
}
