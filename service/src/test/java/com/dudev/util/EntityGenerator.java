package com.dudev.util;

import com.dudev.entity.Brand;
import com.dudev.entity.Category;
import com.dudev.entity.ChangeType;
import com.dudev.entity.Guitar;
import com.dudev.entity.Offer;
import com.dudev.entity.OfferProduct;
import com.dudev.entity.Pedal;
import com.dudev.entity.Product;
import com.dudev.entity.User;
import com.dudev.entity.UserLikedProduct;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

import static com.dudev.entity.Role.ADMIN;

@UtilityClass
public class EntityGenerator {

    public static Brand getBrand(Category category) {
        return Brand.builder()
                .category(category)
                .name("Fender")
                .build();
    }

    public static Category getCategory() {
        return Category.builder()
                .name("guitar")
                .build();
    }

    public static ChangeType getChangeType() {
        return ChangeType.builder()
                .description("Sell")
                .build();
    }

    public static User getUser() {
        return User.builder()
                .username("Mark123")
                .role(ADMIN)
                .fullName("Mark Wahlberg")
                .password("test_password")
                .phoneNumber("8920-122-22-22")
                .build();
    }

    public static Guitar getGuitar(Category category, ChangeType changeType, Brand brand, User user) {
        return Guitar.guitarBuilder()
                .brand(brand)
                .category(category)
                .changeType(changeType)
                .model("Telecaster")
                .description("Good guitar")
                .price(2000)
                .user(user)
                .timestamp(LocalDateTime.of(2022, 2, 1, 0, 0))
                .build();
    }

    public static Pedal getPedal(Category category, ChangeType changeType, Brand brand, User user) {
        return Pedal.pedalBuilder()
                .brand(brand)
                .category(category)
                .changeType(changeType)
                .model("Boss DD-20")
                .description("Good pedal")
                .price(2000)
                .user(user)
                .timestamp(LocalDateTime.of(2022, 2, 1, 0, 0))
                .build();
    }

    public static Product getProduct(Category category, ChangeType changeType, Brand brand, User user) {
        return Product.builder()
                .brand(brand)
                .category(category)
                .changeType(changeType)
                .description("Good pedal")
                .price(2000)
                .user(user)
                .timestamp(LocalDateTime.of(2022, 2, 1, 0, 0))
                .build();
    }

    public static Offer getOffer(User buyer, User seller, ChangeType changeType) {
        return Offer.builder()
                .buyer(buyer)
                .seller(seller)
                .changeType(changeType)
                .timestamp(LocalDateTime.of(2022, 2, 1, 0, 0))
                .build();
    }

    public static OfferProduct getOfferProduct() {
        return OfferProduct.builder()
                .createdAt(LocalDateTime.of(2022, 2, 1, 0, 0))
                .build();
    }

    public static UserLikedProduct getUserLikedProduct() {
        return UserLikedProduct.builder()
                .created_at(LocalDateTime.of(2022, 2, 1, 0, 0))
                .build();
    }
}
