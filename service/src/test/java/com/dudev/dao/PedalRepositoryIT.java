package com.dudev.dao;

import com.dudev.basetest.TransactionManagementTestBase;
import com.dudev.entity.Brand;
import com.dudev.entity.Category;
import com.dudev.entity.ChangeType;
import com.dudev.entity.Guitar;
import com.dudev.entity.Pedal;
import com.dudev.entity.Product;
import com.dudev.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
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

    static PedalRepository pedalRepository;

    @BeforeAll
    static void repoInit() {
        pedalRepository = new PedalRepository(session);
        session.beginTransaction();
        insertEntities(session);
        session.getTransaction().commit();
    }

    @Test
    void save() {
        Pedal entity = getEntity();

        pedalRepository.save(entity);

        assertThat(entity.getId()).isNotNull();
    }

    @Test
    void findById() {
        Pedal entity = getEntity();
        pedalRepository.save(entity);
        session.clear();

        Optional<Pedal> actualEntity = pedalRepository.findById(entity.getId());

        assertThat(actualEntity).isPresent();
        assertThat(actualEntity.get()).isEqualTo(entity);
    }

    @Test
    void delete() {
        Pedal entity = getEntity();
        pedalRepository.save(entity);
        session.clear();

        pedalRepository.delete(entity);

        assertThat(pedalRepository.findById(entity.getId())).isNotPresent();
    }

    @Test
    void update() {
        Pedal entity = getEntity();
        pedalRepository.save(entity);
        session.clear();

        LocalDateTime createdAt = LocalDateTime.of(2000, 1, 1, 2, 2, 1);
        entity.setCreatedAt(createdAt);
        pedalRepository.update(entity);
        session.clear();

        assertThat(pedalRepository.findById(entity.getId()).get()).isEqualTo(entity);
    }

    @Test
    void findAll() {
        Pedal entity = getEntity();
        List<Pedal> actualResult = pedalRepository.findAll();

        assertThat(actualResult.size()).isEqualTo(getEntities().size());
    }

    private static List<Product> getEntities() {
        List<User> users = getUsers();
        List<ChangeType> changeTypes = getChangeTypes();
        List<Category> categories = getCategories();
        List<Brand> brands = getBrands(categories);
        return getProducts(users, changeTypes, categories, brands).stream().filter(it -> it instanceof Pedal).toList();
    }

    private static Pedal getEntity() {
        User user = getUser();
        Category category = getCategory();
        ChangeType changeType = getChangeType();
        Brand brand = getBrand(category);
        Pedal pedal = getPedal(category, changeType, brand, user);


        session.save(user);
        session.save(category);
        session.save(brand);
        session.save(changeType);
        return pedal;
    }

}