package com.dudev.dao;

import com.dudev.basetest.IT;
import com.dudev.basetest.IntegrationTestBase;
import com.dudev.entity.Brand;
import com.dudev.entity.Category;
import com.dudev.util.EntityGenerator;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestConstructor;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.dudev.util.EntityGenerator.getCategories;
import static com.dudev.util.EntityGenerator.getCategory;
import static com.dudev.util.EntityUtil.insertEntities;
import static org.assertj.core.api.Assertions.assertThat;


@RequiredArgsConstructor
class BrandRepositoryIT extends IntegrationTestBase{

    private final BrandRepository brandRepository;

    private final EntityManager entityManager;

    @Test
    void save() {
        Brand entity = getBrand();

        brandRepository.save(entity);

        assertThat(entity.getId()).isNotNull();
    }

    @Test
    void findById() {
        Brand entity = getBrand();
        brandRepository.save(entity);
        entityManager.clear();

        Optional<Brand> actualEntity = brandRepository.findById(entity.getId());

        assertThat(actualEntity).isPresent();
        assertThat(actualEntity.get()).isEqualTo(entity);
    }

    @Test
    void delete() {
        Brand entity = getBrand();
        brandRepository.save(entity);
        entityManager.clear();

        brandRepository.delete(entity);

        assertThat(brandRepository.findById(entity.getId())).isNotPresent();
    }

    @Test
    void update() {
        Brand entity = getBrand();
        brandRepository.save(entity);
        entityManager.clear();

        entity.setName("Aria");
        brandRepository.update(entity);
        entityManager.clear();

        assertThat(brandRepository.findById(entity.getId()).get()).isEqualTo(entity);
    }

    @Test
    void findAll() {
        insertEntities(entityManager);

        List<Brand> actualResult = brandRepository.findAll();
        List<Brand> expectedResult = getBrands();

        assertThat(actualResult.size()).isEqualTo(expectedResult.size());
    }

    private Brand getBrand() {
        Category category = getCategory();
        Brand brand = EntityGenerator.getBrand(category);

        entityManager.persist(category);
        return brand;
    }

    private List<Brand> getBrands() {
        List<Category> categories = getCategories();

        return EntityGenerator.getBrands(categories);
    }
}