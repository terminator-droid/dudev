package com.dudev.dao;

import com.dudev.basetest.TransactionManagementTestBase;
import com.dudev.entity.User;
import com.dudev.entity.Role;
import com.dudev.util.EntityGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static com.dudev.util.EntityUtil.insertEntities;
import static org.assertj.core.api.Assertions.*;

public class UserRepositoryIT extends TransactionManagementTestBase {

    static UserRepository userRepository = applicationContext.getBean(UserRepository.class);

    @Test
    void save() {
        User entity = getUser();

        userRepository.save(entity);

        assertThat(entity.getId()).isNotNull();
    }

    @Test
    void findById() {
        User entity = getUser();
        userRepository.save(entity);
        entityManager.clear();

        Optional<User> actualEntity = userRepository.findById(entity.getId());

        assertThat(actualEntity).isPresent();
        assertThat(actualEntity.get()).isEqualTo(entity);
    }

    @Test
    void delete() {
        User entity = getUser();
        userRepository.save(entity);
        entityManager.clear();

        userRepository.delete(entity);

        assertThat(userRepository.findById(entity.getId())).isNotPresent();
    }

    @Test
    void update() {
        User entity = getUser();
        userRepository.save(entity);
        entityManager.clear();

        entity.setUsername("Username123");
        userRepository.update(entity);
        entityManager.clear();

        assertThat(userRepository.findById(entity.getId()).get()).isEqualTo(entity);
    }

    @Test
    void findAll() {
        insertEntities(entityManager);

        List<User> actualResult = userRepository.findAll();

        assertThat(actualResult.size()).isEqualTo(getUsers().size());
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

    private User getUser() {
        User user = EntityGenerator.getUser();

        return user;
    }

    private List<User> getUsers() {
        List<User> users = EntityGenerator.getUsers();

        return users;
    }
}
