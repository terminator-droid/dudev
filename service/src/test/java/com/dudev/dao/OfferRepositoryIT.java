package com.dudev.dao;

import com.dudev.basetest.TransactionManagementTestBase;
import com.dudev.entity.ChangeType;
import com.dudev.entity.Offer;
import com.dudev.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.dudev.entity.Role.USER;
import static com.dudev.util.EntityGenerator.getChangeType;
import static com.dudev.util.EntityGenerator.getOffer;
import static com.dudev.util.EntityGenerator.getUser;
import static com.dudev.util.EntityUtil.insertEntities;
import static org.assertj.core.api.Assertions.assertThat;

class OfferRepositoryIT extends TransactionManagementTestBase {

    static OfferRepository offerRepository = applicationContext.getBean(OfferRepository.class);

    @Test
    void save() {
        Offer entity = getEntity();

        offerRepository.save(entity);

        assertThat(entity.getId()).isNotNull();
    }

    @Test
    void findById() {
        Offer entity = getEntity();
        offerRepository.save(entity);
        entityManager.clear();

        Optional<Offer> actualEntity = offerRepository.findById(entity.getId());

        assertThat(actualEntity).isPresent();
        assertThat(actualEntity.get()).isEqualTo(entity);
    }

    private Offer getEntity() {
        ChangeType changeType = getChangeType();
        User buyer = getUser();
        User seller = User.builder()
                .username("Gosling333")
                .role(USER)
                .fullName("Ryan Gosling")
                .password("second_password")
                .phoneNumber("8920-122-22-23")
                .build();
        Offer offer = getOffer(buyer, seller, changeType);

        entityManager.persist(seller);
        entityManager.persist(buyer);
        entityManager.persist(changeType);
        return offer;
    }

    @Test
    void delete() {
        Offer entity = getEntity();
        offerRepository.save(entity);
        entityManager.clear();

        offerRepository.delete(entity);

        assertThat(offerRepository.findById(entity.getId())).isNotPresent();
    }

    @Test
    void update() {
        Offer entity = getEntity();
        offerRepository.save(entity);
        entityManager.clear();

        LocalDateTime createdAt = LocalDateTime.of(2000, 1, 1, 2, 2, 1);
        entity.setCreatedAt(createdAt);
        offerRepository.update(entity);
        entityManager.clear();

        assertThat(offerRepository.findById(entity.getId()).get()).isEqualTo(entity);
    }
}