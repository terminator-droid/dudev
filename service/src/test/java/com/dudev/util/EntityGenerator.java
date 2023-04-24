package com.dudev.util;

import com.dudev.entity.Brand;
import com.dudev.entity.Category;
import com.dudev.entity.ChangeType;
import com.dudev.entity.Guitar;
import com.dudev.entity.Offer;
import com.dudev.entity.OfferProduct;
import com.dudev.entity.Pedal;
import com.dudev.entity.Product;
import com.dudev.entity.Role;
import com.dudev.entity.User;
import com.dudev.entity.UserLikedProduct;
import lombok.experimental.UtilityClass;

import java.util.List;

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
        return Guitar.builder()
                .brand(brand)
                .category(category)
                .changeType(changeType)
                .model("Telecaster")
                .description("Good guitar")
                .price(2000)
                .user(user)
                .closed(false)
                .build();
    }

    public static Pedal getPedal(Category category, ChangeType changeType, Brand brand, User user) {
        return Pedal.builder()
                .brand(brand)
                .category(category)
                .changeType(changeType)
                .model("Boss DD-20")
                .description("Good pedal")
                .price(2000)
                .user(user)
                .closed(false)
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
                .closed(false)
                .build();
    }

    public static Offer getOffer(User buyer, User seller, ChangeType changeType) {
        return Offer.builder()
                .buyer(buyer)
                .seller(seller)
                .changeType(changeType)
                .accepted(false)
                .build();
    }

    public static OfferProduct getOfferProduct(Offer offer, Product product) {
        return OfferProduct.builder()
                .offer(offer)
                .product(product)
                .build();
    }

    public static UserLikedProduct getUserLikedProduct(User user, Product product) {
        return UserLikedProduct.builder()
                .user(user)
                .product(product)
                .build();
    }

    public static List<Category> getCategories() {
        Category guitar = Category.builder()
                .name("Guitar")
                .build();
        Category pedal = Category.builder()
                .name("Pedal")
                .build();
        return List.of(guitar, pedal);
    }

    public static List<Brand> getBrands(List<Category> categories) {
        Brand fender = Brand.builder()
                .name("Fender")
                .category(categories.get(0))
                .build();
        Brand gibson = Brand.builder()
                .name("Gibson")
                .category(categories.get(0))
                .build();
        Brand burny = Brand.builder()
                .name("Burny")
                .category(categories.get(0))
                .build();
        Brand boss = Brand.builder()
                .name("Boss")
                .category(categories.get(1))
                .build();
        Brand jazzman = Brand.builder()
                .name("jazzman")
                .category(categories.get(1))
                .build();
        return List.of(fender, gibson, burny, boss, jazzman);
    }

    public static List<ChangeType> getChangeTypes() {
        ChangeType exchange = ChangeType.builder()
                .description("Exchange")
                .build();
        ChangeType sell = ChangeType.builder()
                .description("Sell")
                .build();
        ChangeType exchangeToBuyer = ChangeType.builder()
                .description("Exchange with payment to buyer")
                .build();
        ChangeType exchangeToSeller = ChangeType.builder()
                .description("Exchange with payment to seller")
                .build();
        return List.of(exchange, sell, exchangeToBuyer, exchangeToSeller);
    }

    public static List<User> getUsers() {
        User user1 = User.builder()
                .phoneNumber("89283284834")
                .username("Transformer111")
                .role(Role.USER)
                .fullName("Optimus Prime")
                .password("AutobotsAreTheBest2233")
                .image("10-04-2021_21-21-09_2.jpg")
                .build();
        User user2 = User.builder()
                .phoneNumber("891283284834")
                .username("NightWatcher11")
                .role(Role.USER)
                .fullName("Jon Snow")
                .password("ILoveDragons4Eva")
                .build();
        User user3 = User.builder()
                .phoneNumber("892282284834")
                .username("Morty14")
                .role(Role.USER)
                .fullName("Mortymer")
                .password("Ricjasd")
                .build();
        User user4 = User.builder()
                .phoneNumber("89228124834")
                .username("Pope")
                .role(Role.USER)
                .fullName("Jude Law")
                .password("fwef323ff")
                .build();
        User user5 = User.builder()
                .phoneNumber("892282125834")
                .username("williamson")
                .role(Role.USER)
                .fullName("Prince William")
                .password("sdlpwkoa90e")
                .build();

        return List.of(user1, user2, user3, user4, user5);
    }


    public static List<Product> getProducts(List<User> users, List<ChangeType> changeTypes,
                                            List<Category> categories,
                                            List<Brand> brands) {
        Guitar guitar1 = Guitar.builder()
                .user(users.get(0))
                .price(40000)
                .description("Good guitar")
                .model("Stratocaster")
                .media("guitar_photo")
                .changeType(changeTypes.get(0))
                .changeWish("Telecaster")
                .country("Japan")
                .pickUps("Dimarzio")
                .category(categories.get(0))
                .brand(brands.get(0))
                .wood("Oak")
                .year(1999)
                .closed(false)
                .build();
        Guitar guitar2 = Guitar.builder()
                .user(users.get(1))
                .price(5000)
                .description("Good guitar really")
                .model("Telecaster")
                .media("guitar_photo_2")
                .changeType(changeTypes.get(0))
                .changeWish("Strat")
                .country("Japan")
                .pickUps("Dimarzio")
                .category(categories.get(0))
                .brand(brands.get(1))
                .wood("Ash")
                .year(1999)
                .closed(false)
                .build();

        Guitar guitar3 = Guitar.builder()
                .user(users.get(2))
                .price(40000)
                .description("Broken")
                .model("Les Paul")
                .media("guitar_photo_3")
                .changeType(changeTypes.get(1))
                .changeWish("Telecaster")
                .country("Korea")
                .pickUps("Standard")
                .category(categories.get(0))
                .brand(brands.get(2))
                .wood("Ash")
                .year(2021)
                .closed(false)
                .build();

        Guitar guitar4 = Guitar.builder()
                .user(users.get(3))
                .price(40000)
                .description("Good")
                .model("Les Paul")
                .media("guitar_photo_4")
                .changeType(changeTypes.get(1))
                .changeWish("Gibson les Paul")
                .country("USA")
                .pickUps("Standard")
                .category(categories.get(0))
                .brand(brands.get(2))
                .wood("Ash")
                .year(1988)
                .closed(false)
                .build();
        Pedal pedal = Pedal.builder()
                .user(users.get(3))
                .price(40000)
                .description("Good")
                .model("Looper")
                .media("pedal_photo")
                .changeType(changeTypes.get(3))
                .changeWish("BossDD20")
                .category(categories.get(1))
                .brand(brands.get(3))
                .closed(false)
                .build();
        return List.of(guitar1, guitar2, guitar3, guitar4, pedal);
    }
}
