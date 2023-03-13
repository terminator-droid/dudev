package com.dudev.dao;

import com.dudev.basetest.TransactionManagementTestBase;
import com.dudev.entity.UserLikedProduct;
import com.dudev.entity.UserLikedProduct;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.dudev.util.EntityGenerator.getUserLikedProduct;
import static com.dudev.util.EntityUtil.insertEntities;
import static org.assertj.core.api.Assertions.assertThat;


class UserLikedProductRepositoryIT extends TransactionManagementTestBase {

    static UserLikedProductRepository userLikedProductRepository;

    @BeforeAll
    static void repoInit() {
        userLikedProductRepository = new UserLikedProductRepository(session);
        session.beginTransaction();
        insertEntities(session);
        session.getTransaction().commit();
    }

    @Test
    void save() {
        UserLikedProduct entity = getEntity();

        userLikedProductRepository.save(entity);

        assertThat(entity.getId()).isNotNull();
    }

    @Test
    void findById() {
        UserLikedProduct entity = getEntity();
        userLikedProductRepository.save(entity);
        session.clear();

        Optional<UserLikedProduct> actualEntity = userLikedProductRepository.findById(entity.getId());

        assertThat(actualEntity).isPresent();
        assertThat(actualEntity.get()).isEqualTo(entity);
    }

    @Test
    void delete() {
        UserLikedProduct entity = getEntity();
        userLikedProductRepository.save(entity);
        session.clear();

        userLikedProductRepository.delete(entity);

        assertThat(userLikedProductRepository.findById(entity.getId())).isNotPresent();
    }

    @Test
    void update() {
        UserLikedProduct entity = getEntity();
        userLikedProductRepository.save(entity);
        session.clear();

        LocalDateTime createdAt = LocalDateTime.of(2000, 1, 1, 2, 2, 1);
        entity.setCreatedAt(createdAt);
        userLikedProductRepository.update(entity);
        session.clear();

        assertThat(userLikedProductRepository.findById(entity.getId()).get()).isEqualTo(entity);
    }

    private UserLikedProduct getEntity() {
        return getUserLikedProduct();
    }
}