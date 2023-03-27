package com.dudev.dao;

import com.dudev.basetest.IT;
import com.dudev.basetest.IntegrationTestBase;
import com.dudev.entity.OfferProduct;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.dudev.util.EntityGenerator.getOfferProduct;
import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
class OfferProductIT extends IntegrationTestBase {

    private final OfferProductRepository offerProductRepository;
    private final EntityManager entityManager;

    @Test
    void save() {
        OfferProduct entity = getEntity();

        offerProductRepository.save(entity);

        assertThat(entity.getId()).isNotNull();
    }

    @Test
    void findById() {
        OfferProduct entity = getEntity();
        offerProductRepository.save(entity);
        entityManager.clear();

        Optional<OfferProduct> actualEntity = offerProductRepository.findById(entity.getId());

        assertThat(actualEntity).isPresent();
        assertThat(actualEntity.get()).isEqualTo(entity);
    }

    @Test
    void delete() {
        OfferProduct entity = getEntity();
        offerProductRepository.save(entity);
        entityManager.clear();

        offerProductRepository.delete(entity);

        assertThat(offerProductRepository.findById(entity.getId())).isNotPresent();
    }

    @Test
    void update() {
        OfferProduct entity = getEntity();
        offerProductRepository.save(entity);
        entityManager.clear();

        LocalDateTime createdAt = LocalDateTime.of(2000, 1, 1, 2, 2, 1);
        entity.setCreatedAt(createdAt);
        offerProductRepository.update(entity);
        entityManager.clear();

        assertThat(offerProductRepository.findById(entity.getId()).get()).isEqualTo(entity);
    }

    private OfferProduct getEntity() {
        return getOfferProduct();
    }
}