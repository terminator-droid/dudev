package com.dudev.dao;

import com.dudev.basetest.IT;
import com.dudev.basetest.IntegrationTestBase;
import com.dudev.entity.Category;
import com.dudev.util.EntityGenerator;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.dudev.util.EntityUtil.insertEntities;
import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
class CategoryRepositoryIT extends IntegrationTestBase {

    private final CategoryRepository categoryRepository;

    private final EntityManager entityManager;

    @Test
    void save() {
        Category entity = getCategory();

        categoryRepository.save(entity);

        assertThat(entity.getId()).isNotNull();
    }

    @Test
    void findById() {
        Category entity = getCategory();
        categoryRepository.save(entity);
        entityManager.clear();

        Optional<Category> actualEntity = categoryRepository.findById(entity.getId());

        assertThat(actualEntity).isPresent();
        assertThat(actualEntity.get()).isEqualTo(entity);
    }

    @Test
    void delete() {
        Category entity = getCategory();
        categoryRepository.save(entity);
        entityManager.clear();

        categoryRepository.delete(entity);

        assertThat(categoryRepository.findById(entity.getId())).isNotPresent();
    }

    @Test
    void update() {
        Category entity = getCategory();
        categoryRepository.save(entity);
        entityManager.clear();

        entity.setName("pedal");
        categoryRepository.update(entity);
        entityManager.clear();

        assertThat(categoryRepository.findById(entity.getId()).get()).isEqualTo(entity);
    }

    @Test
    void findAll() {
        insertEntities(entityManager);

        List<Category> actualResult = categoryRepository.findAll();
        List<Category> expectedResult = getCategories();

        assertThat(actualResult.size()).isEqualTo(expectedResult.size());
    }

    private List<Category> getCategories() {
        return EntityGenerator.getCategories();
    }

    private Category getCategory() {
        return EntityGenerator.getCategory();
    }
}