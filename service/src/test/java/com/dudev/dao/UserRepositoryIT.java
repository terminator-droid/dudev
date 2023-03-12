package com.dudev.dao;

import com.dudev.basetest.TransactionManagementTestBase;
import com.dudev.entity.Role;
import com.dudev.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.dudev.util.EntityUtil.insertEntities;
import static org.assertj.core.api.Assertions.*;

public class UserRepositoryIT extends TransactionManagementTestBase {

    static UserRepository userRepository;
    @BeforeAll
    static void repoInit() {
        userRepository = new UserRepository(session);
        session.beginTransaction();
        insertEntities(session);
        session.getTransaction().commit();
    }

    @Test
    void findById() {

    }

    @Test
    void findAll() {
        List<User> users = userRepository.findAll();

        assertThat(users.size()).isEqualTo(5);
    }

    @Test
    void findAllByRole() {
        Role role = Role.USER;

        List<User> users = userRepository.findAllByRole(role);

        users.forEach(it -> assertThat(it.getRole()).isEqualTo(role));
    }

    @Test
    void findLimitedUsersOrderedByFullName() {
        int limit = 3;

        List<User> users = userRepository.findLimitedUsersOrderedByFullName(limit);

        assertThat(users.size()).isEqualTo(limit);
    }
}
