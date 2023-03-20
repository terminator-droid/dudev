package com.dudev.basetest;

import com.dudev.configuration.ApplicationConfiguration;
import com.dudev.configuration.TestApplicationConfiguration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManager;

public abstract class TransactionManagementTestBase {

    protected static ConfigurableApplicationContext applicationContext;
    protected static EntityManager entityManager;


    @BeforeAll
    static void init() {
        applicationContext = new AnnotationConfigApplicationContext(TestApplicationConfiguration.class);
        entityManager = applicationContext.getBean(EntityManager.class);
    }

    @AfterAll
    static void ruinFactory() {
        applicationContext.close();
    }

    @BeforeEach
    void sessionInit() {
        entityManager.getTransaction().begin();
    }

    @AfterEach
    void rollbackTransaction() {
        entityManager.getTransaction().rollback();
    }
}
