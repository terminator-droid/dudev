package com.dudev.dao;

import com.dudev.basetest.IntegrationTestBase;
import com.dudev.entity.Brand;
import com.dudev.entity.Category;
import com.dudev.entity.ChangeType;
import com.dudev.entity.Guitar;
import com.dudev.entity.Offer;
import com.dudev.entity.OfferProduct;
import com.dudev.entity.User;
import com.dudev.util.EntityGenerator;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.dudev.entity.Role.USER;
import static com.dudev.util.EntityGenerator.getBrand;
import static com.dudev.util.EntityGenerator.getCategory;
import static com.dudev.util.EntityGenerator.getChangeType;
import static com.dudev.util.EntityGenerator.getOffer;
import static com.dudev.util.EntityGenerator.getOfferProduct;
import static com.dudev.util.EntityGenerator.getUser;
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
        offerProductRepository.save(entity);
        entityManager.flush();
        entityManager.clear();

        assertThat(offerProductRepository.findById(entity.getId()).get()).isEqualTo(entity);
    }

    private OfferProduct getEntity() {
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
        entityManager.persist(offer);

        Category category = getCategory();
        Brand brand = getBrand(category);
        Guitar product = EntityGenerator.getGuitar(category, changeType, brand, buyer);

        entityManager.persist(category);
        entityManager.persist(brand);
        entityManager.persist(product);

        return getOfferProduct(offer, product);
    }
}