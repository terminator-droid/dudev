package com.dudev.dao;

import com.dudev.basetest.IT;
import com.dudev.basetest.IntegrationTestBase;
import com.dudev.entity.UserLikedProduct;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.dudev.util.EntityGenerator.getUserLikedProduct;
import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
class UserLikedProductRepositoryIT extends IntegrationTestBase {

    private final UserLikedProductRepository userLikedProductRepository;

    private final EntityManager entityManager;

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
        entityManager.clear();

        Optional<UserLikedProduct> actualEntity = userLikedProductRepository.findById(entity.getId());

        assertThat(actualEntity).isPresent();
        assertThat(actualEntity.get()).isEqualTo(entity);
    }

    @Test
    void delete() {
        UserLikedProduct entity = getEntity();
        userLikedProductRepository.save(entity);
        entityManager.clear();

        userLikedProductRepository.delete(entity);

        assertThat(userLikedProductRepository.findById(entity.getId())).isNotPresent();
    }

    @Test
    void update() {
        UserLikedProduct entity = getEntity();
        userLikedProductRepository.save(entity);
        entityManager.clear();

        LocalDateTime createdAt = LocalDateTime.of(2000, 1, 1, 2, 2, 1);
        entity.setCreatedAt(createdAt);
        userLikedProductRepository.update(entity);
        entityManager.clear();

        assertThat(userLikedProductRepository.findById(entity.getId()).get()).isEqualTo(entity);
    }

    private UserLikedProduct getEntity() {
        return getUserLikedProduct();
    }
}