package com.dudev.basetest;

import com.dudev.util.HibernateTestUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public abstract class TransactionManagementTestBase {

    protected static SessionFactory sessionFactory;
    protected Session session;

    @BeforeAll
    static void init() {
        sessionFactory = HibernateTestUtil.buildSessionFactory();
    }

    @AfterAll
    static void ruinFactory() {
        sessionFactory.close();
    }

    @BeforeEach
    void sessionInit() {
        session = sessionFactory.openSession();
        session.beginTransaction();
    }

    @AfterEach
    void rollbackTransaction() {
        session.getTransaction().rollback();
        session.close();
    }
}
