package com.dudev.dao;

import com.dudev.basetest.TransactionManagementTestBase;
import com.dudev.entity.ChangeType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.dudev.util.EntityGenerator.getChangeType;
import static com.dudev.util.EntityGenerator.getChangeTypes;
import static com.dudev.util.EntityUtil.insertEntities;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ChangeTypeRepositoryIT extends TransactionManagementTestBase {

    static ChangeTypeRepository userRepository;

    @BeforeAll
    static void repoInit() {
        userRepository = new ChangeTypeRepository(session);
        session.beginTransaction();
        insertEntities(session);
        session.getTransaction().commit();
    }

    @Test
    void save() {
        ChangeType entity = getEntity();

        userRepository.save(entity);

        assertThat(entity.getId()).isNotNull();
    }

    private ChangeType getEntity() {
        return getChangeType();
    }

    @Test
    void findById() {
        ChangeType entity = getEntity();
        userRepository.save(entity);
        session.clear();

        Optional<ChangeType> actualEntity = userRepository.findById(entity.getId());

        assertThat(actualEntity).isPresent();
        assertThat(actualEntity.get()).isEqualTo(entity);
    }

    @Test
    void delete() {
        ChangeType entity = getEntity();
        userRepository.save(entity);
        session.clear();

        userRepository.delete(entity);

        assertThat(userRepository.findById(entity.getId())).isNotPresent();
    }

    @Test
    void update() {
        ChangeType entity = getEntity();
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
        ChangeType entity = getEntity();
        List<ChangeType> actualResult = userRepository.findAll();

        assertThat(actualResult.size()).isEqualTo(getEntities().size());
    }

    private List<ChangeType> getEntities() {
        return getChangeTypes();
    }

}