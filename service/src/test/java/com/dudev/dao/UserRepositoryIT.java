package com.dudev.dao;

import com.dudev.basetest.IntegrationTestBase;
import com.dudev.entity.Role;
import com.dudev.entity.User;
import com.dudev.util.EntityGenerator;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.dudev.util.EntityUtil.insertEntities;
import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class UserRepositoryIT extends IntegrationTestBase {

    private final UserRepository userRepository;
    private final EntityManager entityManager;

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
        userRepository.save(entity);
        entityManager.flush();
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
        insertEntities(entityManager);
        int limit = 3;

        List<User> users = userRepository.findLimitedUsersOrderedByFullName(limit);

        assertThat(users.size()).isEqualTo(limit);
    }

    private User getUser() {
        return EntityGenerator.getUser();
    }

    private List<User> getUsers() {
        return EntityGenerator.getUsers();
    }
}
