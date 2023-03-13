package com.dudev.dao;

import com.dudev.basetest.TransactionManagementTestBase;
import com.dudev.entity.Role;
import com.dudev.entity.User;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.dudev.util.EntityUtil.insertEntities;
import static org.assertj.core.api.Assertions.*;

public class UserDaoIT extends TransactionManagementTestBase {

    private static final UserDao userDao = UserDao.getInstance();

    @BeforeAll
    static void entitiesInit() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        insertEntities(session);
        session.getTransaction().commit();
        session.close();
    }

    @Test
    void findAll() {
        List<User> users = userDao.findAll(session);

        assertThat(users.size()).isEqualTo(5);
    }

    @Test
    void findAllByRole() {
        Role role = Role.USER;

        List<User> users = userDao.findAllByRole(session, role);

        users.forEach(it -> assertThat(it.getRole()).isEqualTo(role));
    }

    @Test
    void findLimitedUsersOrderedByFullName() {
        int limit = 3;

        List<User> users = userDao.findLimitedUsersOrderedByFullName(session, limit);

        assertThat(users.size()).isEqualTo(limit);
    }
}
