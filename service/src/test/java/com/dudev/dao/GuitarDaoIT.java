package com.dudev.dao;

import com.dudev.basetest.TransactionManagementTestBase;
import com.dudev.dto.GuitarFilter;
import com.dudev.entity.Guitar;

import org.hibernate.Session;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.dudev.util.EntityUtil.insertEntities;
import static org.assertj.core.api.Assertions.assertThat;


public class GuitarDaoIT extends TransactionManagementTestBase {

    private static GuitarDao guitarDao = GuitarDao.getInstance();

    @BeforeAll
    static void entitiesInit() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        insertEntities(session);
        session.getTransaction().commit();
        session.close();
    }

    @Nested
    class QueryDsl {

        @Test
        void findAllGuitars() {
            List<Guitar> guitars = guitarDao.findAllGuitarsQueryDsl(session);

            assertThat(guitars.size()).isEqualTo(4);
        }

        @ParameterizedTest
        @MethodSource("getGuitarFilters")
        void findGuitarsByPredicates(GuitarFilter filter, Object result) {
            List<Guitar> guitarsByPredicates = guitarDao.findAllGuitarsByPredicatesQueryDsl(session, filter);

            if (filter.getYear() != 0) {
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
            List<Guitar> allGuitars = guitarDao.findAllGuitarsCriteria(session);

            assertThat(allGuitars.size()).isEqualTo(4);
        }

        @ParameterizedTest
        @MethodSource("getGuitarFilters")
        void findGuitarsByPredicates(GuitarFilter filter, Object result) {
            List<Guitar> guitarsByPredicates = guitarDao.findGuitarsByPredicatesCriteria(session, filter);

            if (filter.getYear() != 0) {
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
