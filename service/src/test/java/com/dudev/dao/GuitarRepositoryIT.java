package com.dudev.dao;

import com.dudev.basetest.IntegrationTestBase;
import com.dudev.dto.GuitarFilter;
import com.dudev.entity.Brand;
import com.dudev.entity.Category;
import com.dudev.entity.ChangeType;
import com.dudev.entity.Guitar;
import com.dudev.entity.User;
import com.dudev.util.EntityGenerator;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static com.dudev.util.EntityGenerator.getBrand;
import static com.dudev.util.EntityGenerator.getBrands;
import static com.dudev.util.EntityGenerator.getCategories;
import static com.dudev.util.EntityGenerator.getCategory;
import static com.dudev.util.EntityGenerator.getChangeType;
import static com.dudev.util.EntityGenerator.getChangeTypes;
import static com.dudev.util.EntityGenerator.getProducts;
import static com.dudev.util.EntityGenerator.getUser;
import static com.dudev.util.EntityGenerator.getUsers;
import static com.dudev.util.EntityUtil.insertEntities;
import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class GuitarRepositoryIT extends IntegrationTestBase {

    private final GuitarRepository guitarRepository;
    private final EntityManager entityManager;

    @Test
    void persist() {
        Guitar entity = getGuitar();

        guitarRepository.save(entity);

        assertThat(entity.getId()).isNotNull();
    }

    @Test
    void findById() {
        Guitar entity = getGuitar();
        guitarRepository.save(entity);
        entityManager.clear();

        Optional<Guitar> actualEntity = guitarRepository.findById(entity.getId());

        assertThat(actualEntity).isPresent();
        assertThat(actualEntity.get()).isEqualTo(entity);
    }

    @Test
    void delete() {
        Guitar entity = getGuitar();
        guitarRepository.save(entity);
        entityManager.clear();

        guitarRepository.delete(entity);

        assertThat(guitarRepository.findById(entity.getId())).isNotPresent();
    }

    @Test
    void update() {
        Guitar entity = getGuitar();
        guitarRepository.save(entity);
        entityManager.clear();

        entity.setYear(1999);
        guitarRepository.save(entity);
        entityManager.flush();
        entityManager.clear();

        assertThat(guitarRepository.findById(entity.getId()).get()).isEqualTo(entity);
    }

    @Test
    void findAll() {
        insertEntities(entityManager);

        List<Guitar> actualResult = guitarRepository.findAll();
        List<Guitar> expectedResult = getGuitars();

        assertThat(actualResult.size()).isEqualTo(expectedResult.size());
    }

    @Nested
    class QueryDsl {

        @ParameterizedTest
        @MethodSource("getGuitarFilters")
        void findGuitarsByPredicates(GuitarFilter filter, List<Guitar> expectedResult) {
            insertEntities(entityManager);

            List<Guitar> actualResult = guitarRepository.findAllGuitarsByPredicatesQueryDsl(filter);

            assertThat(actualResult.size()).isEqualTo(expectedResult.size());
        }

        @Test
        void findGuitarsByCombinationOfPredicates() {
            insertEntities(entityManager);
            GuitarFilter filterYearModel = GuitarFilter.builder()
                    .year(1999)
                    .model("Stratocaster")
                    .build();

            List<Guitar> allGuitarsByPredicatesQueryDsl = guitarRepository.findAllGuitarsByPredicatesQueryDsl(filterYearModel);

            assertThat(allGuitarsByPredicatesQueryDsl.size()).isEqualTo(1);
        }

        @Test
        void findGuitarsByEmptyFilter() {
            insertEntities(entityManager);
            GuitarFilter filter = GuitarFilter.builder().build();

            List<Guitar> actualResult = guitarRepository.findAllGuitarsByPredicatesQueryDsl(filter);
            List<Guitar> expectedResult = getGuitars();

            assertThat(actualResult.size()).isEqualTo(expectedResult.size());
        }

        static Stream<Arguments> getGuitarFilters() {
            GuitarFilter filterYear = GuitarFilter.builder()
                    .year(1999)
                    .build();
            List<Guitar> yearFilterExpectedResult = getGuitars().stream().filter(it -> it.getYear() == 1999).toList();

            GuitarFilter filterBrand = GuitarFilter.builder()
                    .brand("Fender")
                    .build();
            List<Guitar> brandFilterExpectedResult = getGuitars().stream().filter(it -> it.getBrand().getName().equals("Fender")).toList();

            GuitarFilter filterModel = GuitarFilter.builder()
                    .model("Stratocaster")
                    .build();
            List<Guitar> modelFilterExpectedResult = getGuitars().stream().filter(it -> it.getModel().equals("Stratocaster")).toList();

            GuitarFilter filterChangeWish = GuitarFilter.builder()
                    .changeWish("Telecaster")
                    .build();
            List<Guitar> changeWishFilterExpectedResult = getGuitars().stream().filter(it -> it.getChangeWish().contains("Telecaster")).toList();

            GuitarFilter filterChangeType = GuitarFilter.builder()
                    .changeType("Sell")
                    .build();
            List<Guitar> changeTypeFilterExpectedResult = getGuitars().stream().filter(it -> it.getChangeType().getDescription().equals("Sell")).toList();

            GuitarFilter filterCountry = GuitarFilter.builder()
                    .country("USA")
                    .build();
            List<Guitar> countryFilterExpectedResult = getGuitars().stream().filter(it -> it.getCountry().equals("USA")).toList();

            return Stream.of(
                    Arguments.of(filterYear, yearFilterExpectedResult),
                    Arguments.of(filterBrand, brandFilterExpectedResult),
                    Arguments.of(filterModel, modelFilterExpectedResult),
                    Arguments.of(filterChangeWish, changeWishFilterExpectedResult),
                    Arguments.of(filterChangeType, changeTypeFilterExpectedResult),
                    Arguments.of(filterCountry, countryFilterExpectedResult)
            );
        }

    }

    @Nested
    class CriteriaApi {

        @ParameterizedTest
        @MethodSource("getGuitarFilters")
        void findGuitarsByPredicates(GuitarFilter filter, List<Guitar> expectedResult) {
            insertEntities(entityManager);

            List<Guitar> actualResult = guitarRepository.findAllGuitarsByPredicatesQueryDsl(filter);

            assertThat(actualResult.size()).isEqualTo(expectedResult.size());
        }

        static Stream<Arguments> getGuitarFilters() {
            GuitarFilter filterYear = GuitarFilter.builder()
                    .year(1999)
                    .build();
            List<Guitar> yearFilterExpectedResult = getGuitars().stream().filter(it -> it.getYear() == 1999).toList();

            GuitarFilter filterBrand = GuitarFilter.builder()
                    .brand("Fender")
                    .build();
            List<Guitar> brandFilterExpectedResult = getGuitars().stream().filter(it -> it.getBrand().getName().equals("Fender")).toList();

            GuitarFilter filterModel = GuitarFilter.builder()
                    .model("Stratocaster")
                    .build();
            List<Guitar> modelFilterExpectedResult = getGuitars().stream().filter(it -> it.getModel().equals("Stratocaster")).toList();

            GuitarFilter filterChangeWish = GuitarFilter.builder()
                    .changeWish("Telecaster")
                    .build();
            List<Guitar> changeWishFilterExpectedResult = getGuitars().stream().filter(it -> it.getChangeWish().contains("Telecaster")).toList();

            GuitarFilter filterChangeType = GuitarFilter.builder()
                    .changeType("Sell")
                    .build();
            List<Guitar> changeTypeFilterExpectedResult = getGuitars().stream().filter(it -> it.getChangeType().getDescription().equals("Sell")).toList();

            GuitarFilter filterCountry = GuitarFilter.builder()
                    .country("USA")
                    .build();
            List<Guitar> countryFilterExpectedResult = getGuitars().stream().filter(it -> it.getCountry().equals("USA")).toList();

            return Stream.of(
                    Arguments.of(filterYear, yearFilterExpectedResult),
                    Arguments.of(filterBrand, brandFilterExpectedResult),
                    Arguments.of(filterModel, modelFilterExpectedResult),
                    Arguments.of(filterChangeWish, changeWishFilterExpectedResult),
                    Arguments.of(filterChangeType, changeTypeFilterExpectedResult),
                    Arguments.of(filterCountry, countryFilterExpectedResult)
            );
        }
    }

    private Guitar getGuitar() {
        User user = getUser();
        Category category = getCategory();
        ChangeType changeType = getChangeType();
        Brand brand = getBrand(category);
        Guitar guitar = EntityGenerator.getGuitar(category, changeType, brand, user);

        entityManager.persist(user);
        entityManager.persist(category);
        entityManager.persist(brand);
        entityManager.persist(changeType);
        return guitar;
    }

    private static List<Guitar> getGuitars() {
        List<User> users = getUsers();
        List<ChangeType> changeTypes = getChangeTypes();
        List<Category> categories = getCategories();
        List<Brand> brands = getBrands(categories);
        List<Guitar> guitars = new ArrayList<>();

        getProducts(users, changeTypes, categories, brands).stream()
                .filter(it -> it instanceof Guitar)
                .forEach(guitar -> guitars.add((Guitar) guitar));
        return guitars;
    }
}
