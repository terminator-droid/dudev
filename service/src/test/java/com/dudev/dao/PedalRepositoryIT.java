package com.dudev.dao;

import com.dudev.basetest.TransactionManagementTestBase;
import com.dudev.entity.Brand;
import com.dudev.entity.Category;
import com.dudev.entity.ChangeType;
import com.dudev.entity.Guitar;
import com.dudev.entity.Pedal;
import com.dudev.entity.Product;
import com.dudev.entity.User;
import com.dudev.util.EntityGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.dudev.util.EntityGenerator.getBrand;
import static com.dudev.util.EntityGenerator.getBrands;
import static com.dudev.util.EntityGenerator.getCategories;
import static com.dudev.util.EntityGenerator.getCategory;
import static com.dudev.util.EntityGenerator.getChangeType;
import static com.dudev.util.EntityGenerator.getChangeTypes;
import static com.dudev.util.EntityGenerator.getPedal;
import static com.dudev.util.EntityGenerator.getProducts;
import static com.dudev.util.EntityGenerator.getUser;
import static com.dudev.util.EntityGenerator.getUsers;
import static com.dudev.util.EntityUtil.insertEntities;
import static org.assertj.core.api.Assertions.assertThat;

class PedalRepositoryIT extends TransactionManagementTestBase {

    static PedalRepository pedalRepository = applicationContext.getBean(PedalRepository.class);

    @Test
    void save() {
        Pedal entity = getPedal();

        pedalRepository.save(entity);

        assertThat(entity.getId()).isNotNull();
    }

    @Test
    void findById() {
        Pedal entity = getPedal();
        pedalRepository.save(entity);
        entityManager.clear();

        Optional<Pedal> actualEntity = pedalRepository.findById(entity.getId());

        assertThat(actualEntity).isPresent();
        assertThat(actualEntity.get()).isEqualTo(entity);
    }

    @Test
    void delete() {
        Pedal entity = getPedal();
        pedalRepository.save(entity);
        entityManager.clear();

        pedalRepository.delete(entity);

        assertThat(pedalRepository.findById(entity.getId())).isNotPresent();
    }

    @Test
    void update() {
        Pedal entity = getPedal();
        pedalRepository.save(entity);
        entityManager.clear();

        LocalDateTime createdAt = LocalDateTime.of(2000, 1, 1, 2, 2, 1);
        entity.setCreatedAt(createdAt);
        pedalRepository.update(entity);
        entityManager.clear();

        assertThat(pedalRepository.findById(entity.getId()).get()).isEqualTo(entity);
    }

    @Test
    void findAll() {
        insertEntities(entityManager);

        Pedal entity = getPedal();
        List<Pedal> actualResult = pedalRepository.findAll();

        assertThat(actualResult.size()).isEqualTo(getPedals().size());
    }

    private static Pedal getPedal() {
        User user = getUser();
        Category category = getCategory();
        ChangeType changeType = getChangeType();
        Brand brand = getBrand(category);
        Pedal pedal = EntityGenerator.getPedal(category, changeType, brand, user);

        entityManager.persist(user);
        entityManager.persist(category);
        entityManager.persist(brand);
        entityManager.persist(changeType);
        return pedal;
    }

    private static List<Pedal> getPedals() {
        List<User> users = getUsers();
        List<ChangeType> changeTypes = getChangeTypes();
        List<Category> categories = getCategories();
        List<Brand> brands = getBrands(categories);
        List<Pedal> guitars = new ArrayList<>();

        getProducts(users, changeTypes, categories, brands).stream()
                .filter(it -> it instanceof Guitar)
                .forEach(guitar -> guitars.add((Pedal) guitar));
        return guitars;
    }
}