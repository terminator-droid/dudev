package com.dudev.entity;

import com.dudev.basetest.TransactionManagementTestBase;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.dudev.entity.Role.USER;
import static com.dudev.util.EntityGenerator.getBrand;
import static com.dudev.util.EntityGenerator.getOfferProduct;
import static com.dudev.util.EntityGenerator.getCategory;
import static com.dudev.util.EntityGenerator.getChangeType;
import static com.dudev.util.EntityGenerator.getOffer;
import static com.dudev.util.EntityGenerator.getProduct;
import static com.dudev.util.EntityGenerator.getUser;
import static org.assertj.core.api.Assertions.assertThat;

class OfferProductIT extends TransactionManagementTestBase {

    @Test
    void save() {
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
        Category category = getCategory();
        Brand brand = getBrand(category);
        Product product = getProduct(category, changeType, brand, buyer);
        OfferProduct offerProduct = getOfferProduct();
        offerProduct.setProduct(product);
        offerProduct.setOffer(offer);
        session.save(category);
        session.save(brand);
        session.save(changeType);
        session.save(seller);
        session.save(buyer);
        session.save(offer);
        session.save(product);

        session.save(offerProduct);

        assertThat(offerProduct.getId()).isNotNull();
    }

    @Test
    void get() {
        OfferProduct offerProduct = saveOfferProduct();
        session.clear();

        OfferProduct actualOfferProduct = session.get(OfferProduct.class, offerProduct.getId());

        assertThat(actualOfferProduct).isEqualTo(offerProduct);
    }

    @Test
    void update() {
        OfferProduct initialOfferProduct = saveOfferProduct();
        session.clear();

        initialOfferProduct.setCreatedAt(LocalDateTime.of(2022, 2, 2, 0, 0));
        session.update(initialOfferProduct);
        session.flush();
        session.clear();
        OfferProduct updatedOfferProduct = session.get(OfferProduct.class, initialOfferProduct.getId());

        assertThat(updatedOfferProduct.getCreatedAt()).isEqualTo(initialOfferProduct.getCreatedAt());
    }

    @Test
    void delete() {
        OfferProduct offerProduct = saveOfferProduct();
        session.clear();

        session.delete(offerProduct);
        OfferProduct deletedOfferProduct = session.get(OfferProduct.class, offerProduct.getId());

        assertThat(deletedOfferProduct).isNull();
    }

    private OfferProduct saveOfferProduct() {
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
        Category category = getCategory();
        Brand brand = getBrand(category);
        Product product = getProduct(category, changeType, brand, buyer);
        OfferProduct offerProduct = getOfferProduct();
        offerProduct.setProduct(product);
        offerProduct.setOffer(offer);

        session.save(category);
        session.save(brand);
        session.save(changeType);
        session.save(seller);
        session.save(buyer);
        session.save(offer);
        session.save(product);
        session.save(offerProduct);

        return offerProduct;
    }

}
