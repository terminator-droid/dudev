package com.dudev.dao;

import com.dudev.basetest.TransactionManagementTestBase;
import com.dudev.entity.OfferProduct;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.dudev.util.EntityGenerator.getOfferProduct;
import static com.dudev.util.EntityUtil.insertEntities;
import static org.assertj.core.api.Assertions.assertThat;


class OfferProductIT extends TransactionManagementTestBase {

    static OfferProductRepository offerProductRepository;

    @BeforeAll
    static void repoInit() {
        OfferProductIT.offerProductRepository = new OfferProductRepository(session);
        session.beginTransaction();
        insertEntities(session);
        session.getTransaction().commit();
    }

    @Test
    void save() {
        OfferProduct entity = getEntity();

        OfferProductIT.offerProductRepository.save(entity);

        assertThat(entity.getId()).isNotNull();
    }

    @Test
    void findById() {
        OfferProduct entity = getEntity();
        OfferProductIT.offerProductRepository.save(entity);
        session.clear();

        Optional<OfferProduct> actualEntity = OfferProductIT.offerProductRepository.findById(entity.getId());

        assertThat(actualEntity).isPresent();
        assertThat(actualEntity.get()).isEqualTo(entity);
    }

    @Test
    void delete() {
        OfferProduct entity = getEntity();
        OfferProductIT.offerProductRepository.save(entity);
        session.clear();

        OfferProductIT.offerProductRepository.delete(entity);

        assertThat(OfferProductIT.offerProductRepository.findById(entity.getId())).isNotPresent();
    }

    @Test
    void update() {
        OfferProduct entity = getEntity();
        OfferProductIT.offerProductRepository.save(entity);
        session.clear();

        LocalDateTime createdAt = LocalDateTime.of(2000, 1, 1, 2, 2, 1);
        entity.setCreatedAt(createdAt);
        OfferProductIT.offerProductRepository.update(entity);
        session.clear();

        assertThat(OfferProductIT.offerProductRepository.findById(entity.getId()).get()).isEqualTo(entity);
    }

    private OfferProduct getEntity() {
        return getOfferProduct();
    }
}