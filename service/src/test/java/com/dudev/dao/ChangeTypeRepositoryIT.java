package com.dudev.dao;

import com.dudev.basetest.TransactionManagementTestBase;
import com.dudev.entity.ChangeType;
import com.dudev.util.EntityGenerator;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.dudev.util.EntityUtil.insertEntities;
import static org.assertj.core.api.Assertions.assertThat;

class ChangeTypeRepositoryIT extends TransactionManagementTestBase {

    static ChangeTypeRepository userRepository = applicationContext.getBean(ChangeTypeRepository.class);

    @Test
    void save() {
        ChangeType entity = getChangeType();

        userRepository.save(entity);

        assertThat(entity.getId()).isNotNull();
    }

    @Test
    void findById() {
        ChangeType entity = getChangeType();
        userRepository.save(entity);
        entityManager.clear();

        Optional<ChangeType> actualEntity = userRepository.findById(entity.getId());

        assertThat(actualEntity).isPresent();
        assertThat(actualEntity.get()).isEqualTo(entity);
    }

    @Test
    void delete() {
        ChangeType entity = getChangeType();
        userRepository.save(entity);
        entityManager.clear();

        userRepository.delete(entity);

        assertThat(userRepository.findById(entity.getId())).isNotPresent();
    }

    @Test
    void update() {
        ChangeType entity = getChangeType();
        userRepository.save(entity);
        entityManager.clear();

        entity.setDescription("Change");
        userRepository.update(entity);
        entityManager.clear();

        assertThat(userRepository.findById(entity.getId()).get()).isEqualTo(entity);
    }

    @Test
    void findAll() {
        insertEntities(entityManager);

        List<ChangeType> actualResult = userRepository.findAll();

        assertThat(actualResult.size()).isEqualTo(getChangeTypes().size());
    }

    private List<ChangeType> getChangeTypes() {
        return EntityGenerator.getChangeTypes();
    }


    private ChangeType getChangeType() {
        return EntityGenerator.getChangeType();
    }
}