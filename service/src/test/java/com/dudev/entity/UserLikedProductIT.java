package com.dudev.entity;

import com.dudev.basetest.TransactionManagementTestBase;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.dudev.entity.Role.USER;
import static com.dudev.util.EntityGenerator.getBrand;
import static com.dudev.util.EntityGenerator.getUserLikedProduct;
import static com.dudev.util.EntityGenerator.getCategory;
import static com.dudev.util.EntityGenerator.getChangeType;
import static com.dudev.util.EntityGenerator.getProduct;
import static com.dudev.util.EntityGenerator.getUser;
import static org.assertj.core.api.Assertions.assertThat;

class UserLikedProductIT extends TransactionManagementTestBase {

    @Test
    void save() {
        Category category = getCategory();
        Brand brand = getBrand(category);
        ChangeType changeType = getChangeType();
        User user = getUser();
        User userLike = User.builder()
                .username("Gosling333")
                .role(USER)
                .fullName("Ryan Gosling")
                .password("second_password")
                .phoneNumber("8920-122-22-23")
                .build();
        Product product = getProduct(category, changeType, brand, user);
        UserLikedProduct userLikedProduct = getUserLikedProduct();
        userLikedProduct.setProduct(product);
        userLikedProduct.setUser(user);
        session.save(category);
        session.save(brand);
        session.save(changeType);
        session.save(user);
        session.save(userLike);
        session.save(product);

        session.save(userLikedProduct);

        assertThat(userLikedProduct.getId()).isNotNull();
    }

    @Test
    void get() {
        UserLikedProduct userLikedProduct = saveUserLikedProduct();
        session.clear();
        UserLikedProduct actualUserLikedProduct = session.get(UserLikedProduct.class, userLikedProduct.getId());

        assertThat(actualUserLikedProduct).isEqualTo(userLikedProduct);
    }

    @Test
    void update() {
        UserLikedProduct initialUserLikedProduct = saveUserLikedProduct();
        session.clear();
        initialUserLikedProduct.setCreated_at(LocalDateTime.of(2022, 2, 2, 0, 0));
        session.update(initialUserLikedProduct);
        session.flush();
        session.clear();

        UserLikedProduct updatedUserLikedProduct = session.get(UserLikedProduct.class, initialUserLikedProduct.getId());

        assertThat(updatedUserLikedProduct).isEqualTo(initialUserLikedProduct);
    }

    @Test
    void delete() {
        UserLikedProduct userLikedProduct = saveUserLikedProduct();
        session.clear();

        session.delete(userLikedProduct);
        UserLikedProduct deletedUserLikedProduct = session.get(UserLikedProduct.class, userLikedProduct.getId());

        assertThat(deletedUserLikedProduct).isNull();
    }

    private UserLikedProduct saveUserLikedProduct() {
        Category category = getCategory();
        Brand brand = getBrand(category);
        ChangeType changeType = getChangeType();
        User user = getUser();
        User userLike = User.builder()
                .username("Gosling333")
                .role(USER)
                .fullName("Ryan Gosling")
                .password("second_password")
                .phoneNumber("8920-122-22-23")
                .build();
        Product product = getProduct(category, changeType, brand, user);

        UserLikedProduct userLikedProduct = getUserLikedProduct();
        userLikedProduct.setProduct(product);
        userLikedProduct.setUser(user);

        session.save(category);
        session.save(brand);
        session.save(changeType);
        session.save(user);
        session.save(userLike);
        session.save(product);
        session.save(userLikedProduct);
        return userLikedProduct;
    }

}
