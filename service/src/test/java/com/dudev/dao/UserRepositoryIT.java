package com.dudev.dao;

import com.dudev.basetest.TransactionManagementTestBase;
import com.dudev.entity.User;
import com.dudev.entity.Role;
import com.dudev.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.dudev.util.EntityGenerator.getUser;
import static com.dudev.util.EntityGenerator.getUsers;
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
    void save() {
        User entity = getEntity();

        userRepository.save(entity);

        assertThat(entity.getId()).isNotNull();
    }

    @Test
    void findById() {
        User entity = getEntity();
        userRepository.save(entity);
        session.clear();

        Optional<User> actualEntity = userRepository.findById(entity.getId());

        assertThat(actualEntity).isPresent();
        assertThat(actualEntity.get()).isEqualTo(entity);
    }

    @Test
    void delete() {
        User entity = getEntity();
        userRepository.save(entity);
        session.clear();

        userRepository.delete(entity);

        assertThat(userRepository.findById(entity.getId())).isNotPresent();
    }

    @Test
    void update() {
        User entity = getEntity();
        userRepository.save(entity);
        session.clear();

        LocalDateTime createdAt = LocalDateTime.of(2000, 1, 1, 2, 2, 1);
        entity.setCreatedAt(createdAt);
        userRepository.update(entity);
        session.clear();

        assertThat(userRepository.findById(entity.getId()).get()).isEqualTo(entity);
    }

    @Test
    void findAll() {
        List<User> actualResult = userRepository.findAll();

        assertThat(actualResult.size()).isEqualTo(getEntities().size());
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

    private User getEntity() {
        User user = getUser();

        return user;
    }

    private List<User> getEntities() {
        List<User> users = getUsers();

        return users;
    }
}
