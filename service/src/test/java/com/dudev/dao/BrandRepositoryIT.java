package com.dudev.dao;

import com.dudev.basetest.TransactionManagementTestBase;
import com.dudev.entity.Brand;
import com.dudev.entity.Category;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.dudev.util.EntityGenerator.getBrand;
import static com.dudev.util.EntityGenerator.getBrands;
import static com.dudev.util.EntityGenerator.getCategories;
import static com.dudev.util.EntityGenerator.getCategory;
import static com.dudev.util.EntityUtil.insertEntities;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BrandRepositoryIT extends TransactionManagementTestBase {

    static BrandRepository brandRepository;

    @BeforeAll
    static void repoInit() {
        brandRepository = new BrandRepository(session);
        session.beginTransaction();
        insertEntities(session);
        session.getTransaction().commit();
    }

    @Test
    void save() {
        Brand entity = getEntity();

        brandRepository.save(entity);

        assertThat(entity.getId()).isNotNull();
    }

    @Test
    void findById() {
        Brand entity = getEntity();
        brandRepository.save(entity);
        session.clear();

        Optional<Brand> actualEntity = brandRepository.findById(entity.getId());

        assertThat(actualEntity).isPresent();
        assertThat(actualEntity.get()).isEqualTo(entity);
    }

    @Test
    void delete() {
        Brand entity = getEntity();
        brandRepository.save(entity);
        session.clear();

        brandRepository.delete(entity);

        assertThat(brandRepository.findById(entity.getId())).isNotPresent();
    }

    @Test
    void update() {
        Brand entity = getEntity();
        brandRepository.save(entity);
        session.clear();

        LocalDateTime createdAt = LocalDateTime.of(2000, 1, 1, 2, 2, 1);
        entity.setCreatedAt(createdAt);
        brandRepository.update(entity);
        session.clear();

        assertThat(brandRepository.findById(entity.getId()).get()).isEqualTo(entity);
    }

    @Test
    void findAll() {
        List<Brand> actualResult = brandRepository.findAll();

        assertThat(actualResult.size()).isEqualTo(getEntities().size());
    }

    private Brand getEntity() {
        Category category = getCategory();
        Brand brand = getBrand(category);

        session.save(category);
        return brand;
    }

    private List<Brand> getEntities() {
        List<Category> categories = getCategories();
        List<Brand> brands = getBrands(categories);

        return brands;
    }
}