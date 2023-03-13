package com.dudev.basetest;

import com.dudev.dao.UserRepositoryIT;
import com.dudev.util.HibernateTestUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.lang.reflect.Proxy;

public abstract class TransactionManagementTestBase {

    protected static SessionFactory sessionFactory;
    protected static Session session;

    @BeforeAll
    static void init() {
        sessionFactory = HibernateTestUtil.buildSessionFactory();
        session = (Session) Proxy.newProxyInstance(UserRepositoryIT.class.getClassLoader(), new Class[]{Session.class},
                (proxy, method, args) -> method.invoke(sessionFactory.getCurrentSession(), args));
    }

    @AfterAll
    static void ruinFactory() {
        sessionFactory.close();
    }

    @BeforeEach
    void sessionInit() {
//        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
    }

    @AfterEach
    void rollbackTransaction() {
        session.getTransaction().rollback();
    }
}
