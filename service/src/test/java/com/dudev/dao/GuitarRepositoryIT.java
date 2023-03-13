package com.dudev.dao;

import com.dudev.basetest.TransactionManagementTestBase;
import com.dudev.dto.GuitarFilter;
import com.dudev.entity.Brand;
import com.dudev.entity.Category;
import com.dudev.entity.ChangeType;
import com.dudev.entity.Guitar;

import com.dudev.entity.Product;
import com.dudev.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.dudev.util.EntityGenerator.getBrand;
import static com.dudev.util.EntityGenerator.getBrands;
import static com.dudev.util.EntityGenerator.getCategories;
import static com.dudev.util.EntityGenerator.getCategory;
import static com.dudev.util.EntityGenerator.getChangeType;
import static com.dudev.util.EntityGenerator.getChangeTypes;
import static com.dudev.util.EntityGenerator.getGuitar;
import static com.dudev.util.EntityGenerator.getProducts;
import static com.dudev.util.EntityGenerator.getUser;
import static com.dudev.util.EntityGenerator.getUsers;
import static com.dudev.util.EntityUtil.insertEntities;
import static org.assertj.core.api.Assertions.assertThat;


public class GuitarRepositoryIT extends TransactionManagementTestBase {

    static GuitarRepository guitarRepository;

    @BeforeAll
    static void entitiesInit() {
        guitarRepository = new GuitarRepository(session);
        session.beginTransaction();
        insertEntities(session);
        session.getTransaction().commit();
    }

    @Test
    void save() {
        Guitar entity = getEntity();

        guitarRepository.save(entity);

        assertThat(entity.getId()).isNotNull();
    }

    @Test
    void findById() {
        Guitar entity = getEntity();
        guitarRepository.save(entity);
        session.clear();

        Optional<Guitar> actualEntity = guitarRepository.findById(entity.getId());

        assertThat(actualEntity).isPresent();
        assertThat(actualEntity.get()).isEqualTo(entity);
    }

    @Test
    void delete() {
        Guitar entity = getEntity();
        guitarRepository.save(entity);
        session.clear();

        guitarRepository.delete(entity);

        assertThat(guitarRepository.findById(entity.getId())).isNotPresent();
    }

    @Test
    void update() {
        Guitar entity = getEntity();
        guitarRepository.save(entity);
        session.clear();

        LocalDateTime createdAt = LocalDateTime.of(2000, 1, 1, 2, 2, 1);
        entity.setCreatedAt(createdAt);
        guitarRepository.update(entity);
        session.clear();

        assertThat(guitarRepository.findById(entity.getId()).get()).isEqualTo(entity);
    }

    @Test
    void findAll() {
        Guitar entity = getEntity();
        List<Guitar> actualResult = guitarRepository.findAll();

        assertThat(actualResult.size()).isEqualTo(getEntities().size());
    }

    @Nested
    class QueryDsl {

        @ParameterizedTest
        @MethodSource("getGuitarFilters")
        void findGuitarsByPredicates(GuitarFilter filter, Object result) {
            List<Guitar> guitarsByPredicates = guitarRepository.findAllGuitarsByPredicatesQueryDsl(filter);

            if (filter.getYear() != null) {
                guitarsByPredicates
                        .forEach(it -> assertThat(it.getYear()).isEqualTo(result));
            }
            if (filter.getModel() != null) {
                guitarsByPredicates
                        .forEach(it -> assertThat(it.getModel()).isEqualTo(result));
            }
            if (filter.getChangeType() != null) {
                guitarsByPredicates
                        .forEach(it -> assertThat(it.getChangeType().getDescription()).isEqualTo(result));
            }
            if (filter.getChangeWish() != null) {
                guitarsByPredicates
                        .forEach(it -> assertThat(it.getChangeWish()).containsIgnoringCase((CharSequence) result));
            }
            if (filter.getCountry() != null) {
                guitarsByPredicates
                        .forEach(it -> assertThat(it.getCountry()).isEqualTo(result));
            }
            if (filter.getBrand() != null) {
                guitarsByPredicates
                        .forEach(it -> assertThat(it.getBrand().getName()).isEqualTo(result));
            }
        }

        @Test
        void findGuitarsByCombinationOfPredicates() {
            GuitarFilter filterYearModel = GuitarFilter.builder()
                    .year(1999)
                    .model("Stratocaster")
                    .build();

            List<Guitar> allGuitarsByPredicatesQueryDsl = guitarRepository.findAllGuitarsByPredicatesQueryDsl(filterYearModel);

            assertThat(allGuitarsByPredicatesQueryDsl.size()).isEqualTo(1);
        }

        @Test
        void findGuitarsByEmptyFilter() {
            GuitarFilter filter = GuitarFilter.builder().build();

            List<Guitar> allGuitarsByPredicatesQueryDsl = guitarRepository.findAllGuitarsByPredicatesQueryDsl(filter);

            assertThat(allGuitarsByPredicatesQueryDsl.size()).isEqualTo(getEntities().size());
        }

        static Stream<Arguments> getGuitarFilters() {
            GuitarFilter filterYear = GuitarFilter.builder()
                    .year(1999)
                    .build();
            GuitarFilter filterBrand = GuitarFilter.builder()
                    .brand("Fender")
                    .build();
            GuitarFilter filterModel = GuitarFilter.builder()
                    .model("Stratocaster")
                    .build();
            GuitarFilter filterChangeWish = GuitarFilter.builder()
                    .changeWish("Telecaster")
                    .build();
            GuitarFilter filterChangeType = GuitarFilter.builder()
                    .changeType("Sell")
                    .build();
            GuitarFilter filterCountry = GuitarFilter.builder()
                    .country("USA")
                    .build();

            return Stream.of(
                    Arguments.of(filterYear, 1999),
                    Arguments.of(filterBrand, "Fender"),
                    Arguments.of(filterModel, "Stratocaster"),
                    Arguments.of(filterChangeWish, "Telecaster"),
                    Arguments.of(filterChangeType, "Sell"),
                    Arguments.of(filterCountry, "USA")
            );
        }

    }

    @Nested
    class CriteriaApi {

        @Test
        void findAllGuitars() {
            List<Guitar> allGuitars = guitarRepository.findAllGuitarsCriteria();

            assertThat(allGuitars.size()).isEqualTo(4);
        }

        @ParameterizedTest
        @MethodSource("getGuitarFilters")
        void findGuitarsByPredicates(GuitarFilter filter, Object result) {
            List<Guitar> guitarsByPredicates = guitarRepository.findGuitarsByPredicatesCriteria(filter);

            if (filter.getYear() != null) {
                guitarsByPredicates
                        .forEach(it -> assertThat(it.getYear()).isEqualTo(result));
            }
            if (filter.getModel() != null) {
                guitarsByPredicates
                        .forEach(it -> assertThat(it.getModel()).isEqualTo(result));
            }
            if (filter.getChangeType() != null) {
                guitarsByPredicates
                        .forEach(it -> assertThat(it.getChangeType().getDescription()).isEqualTo(result));
            }
            if (filter.getChangeWish() != null) {
                guitarsByPredicates
                        .forEach(it -> assertThat(it.getChangeWish()).containsIgnoringCase((CharSequence) result));
            }
            if (filter.getCountry() != null) {
                guitarsByPredicates
                        .forEach(it -> assertThat(it.getCountry()).isEqualTo(result));
            }
            if (filter.getBrand() != null) {
                guitarsByPredicates
                        .forEach(it -> assertThat(it.getBrand().getName()).isEqualTo(result));
            }
        }

        static Stream<Arguments> getGuitarFilters() {
            GuitarFilter filterYear = GuitarFilter.builder()
                    .year(1999)
                    .build();
            GuitarFilter filterBrand = GuitarFilter.builder()
                    .brand("Fender")
                    .build();
            GuitarFilter filterModel = GuitarFilter.builder()
                    .model("Stratocaster")
                    .build();
            GuitarFilter filterChangeWish = GuitarFilter.builder()
                    .changeWish("Telecaster")
                    .build();
            GuitarFilter filterChangeType = GuitarFilter.builder()
                    .changeType("Sell")
                    .build();
            GuitarFilter filterCountry = GuitarFilter.builder()
                    .country("USA")
                    .build();

            return Stream.of(
                    Arguments.of(filterYear, 1999),
                    Arguments.of(filterBrand, "Fender"),
                    Arguments.of(filterModel, "Stratocaster"),
                    Arguments.of(filterChangeWish, "Telecaster"),
                    Arguments.of(filterChangeType, "Sell"),
                    Arguments.of(filterCountry, "USA")
            );
        }
    }

    private static Guitar getEntity() {
        User user = getUser();
        Category category = getCategory();
        ChangeType changeType = getChangeType();
        Brand brand = getBrand(category);
        Guitar guitar = getGuitar(category, changeType, brand, user);

        session.save(user);
        session.save(category);
        session.save(brand);
        session.save(changeType);
        return guitar;
    }

    private static List<Product> getEntities() {
        List<User> users = getUsers();
        List<ChangeType> changeTypes = getChangeTypes();
        List<Category> categories = getCategories();
        List<Brand> brands = getBrands(categories);
        List<Product> entities = getProducts(users, changeTypes, categories, brands).stream().filter(it -> it instanceof Guitar).toList();
        return entities;
    }
}
