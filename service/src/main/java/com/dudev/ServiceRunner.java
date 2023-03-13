package com.dudev;

import com.dudev.util.EntityUtil;
import com.dudev.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ServiceRunner {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        EntityUtil.insertEntities(session);
        session.getTransaction().commit();
        session.close();

    }
}
