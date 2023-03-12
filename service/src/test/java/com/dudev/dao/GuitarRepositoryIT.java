package com.dudev.dao;

import com.dudev.basetest.RepositoryTestBase;
import com.dudev.dto.GuitarFilter;
import com.dudev.entity.Brand;
import com.dudev.entity.Category;
import com.dudev.entity.ChangeType;
import com.dudev.entity.Guitar;

import com.dudev.entity.Product;
import com.dudev.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.dudev.util.EntityGenerator.*;
import static com.dudev.util.EntityGenerator.getBrand;
import static com.dudev.util.EntityGenerator.getCategory;
import static com.dudev.util.EntityGenerator.getChangeType;
import static com.dudev.util.EntityGenerator.getGuitar;
import static com.dudev.util.EntityGenerator.getUser;
import static com.dudev.util.EntityUtil.insertEntities;
import static org.assertj.core.api.Assertions.assertThat;


public class GuitarRepositoryIT extends RepositoryTestBase<Integer, Guitar> {

    static GuitarRepository guitarRepository;

    public GuitarRepositoryIT() {
        super(guitarRepository, getEntity());
    }

    public static Guitar getEntity() {
        User user = getUser();
        Category category = getCategory();
        ChangeType changeType = getChangeType();
        Brand brand = getBrand(category);
        Guitar guitar = getGuitar(category, changeType, brand, user);

        session.beginTransaction();
        session.save(user);
        session.save(category);
        session.save(brand);
        session.save(changeType);
        session.getTransaction().commit();
        return guitar;
    }

//    public static List<Guitar> getEntities() {
//        List<User> users = getUsers();
//        List<ChangeType> changeTypes = getChangeTypes();
//        List<Category> categories = getCategories();
//        List<Brand> brands = getBrands(categories);
//        List<Guitar> entities = (List<Guitar>) (Guitar) getProducts(users, changeTypes, categories, brands).stream().filter(it ->  it instanceof Guitar).toList();
//        return entities;
//
//    }

    @BeforeAll
    static void entitiesInit() {
        guitarRepository = new GuitarRepository(session);
        session.beginTransaction();
        insertEntities(session);
        session.getTransaction().commit();
    }

    @Nested
    class QueryDsl {

        @Test
        void findAllGuitars() {
            List<Guitar> guitars = guitarRepository.findAllGuitarsQueryDsl(session);

            assertThat(guitars.size()).isEqualTo(4);
        }

        @ParameterizedTest
        @MethodSource("getGuitarFilters")
        void findGuitarsByPredicates(GuitarFilter filter, Object result) {
            List<Guitar> guitarsByPredicates = guitarRepository.findAllGuitarsByPredicatesQueryDsl(session, filter);

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

    @Nested
    class CriteriaApi {
        @Test
        void findAllGuitars() {
            List<Guitar> allGuitars = guitarRepository.findAllGuitarsCriteria(session);

            assertThat(allGuitars.size()).isEqualTo(4);
        }

        @ParameterizedTest
        @MethodSource("getGuitarFilters")
        void findGuitarsByPredicates(GuitarFilter filter, Object result) {
            List<Guitar> guitarsByPredicates = guitarRepository.findGuitarsByPredicatesCriteria(session, filter);

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
}
