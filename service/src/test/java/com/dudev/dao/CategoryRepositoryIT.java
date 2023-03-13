package com.dudev.dao;

import com.dudev.basetest.TransactionManagementTestBase;
import com.dudev.entity.Category;
import com.dudev.util.EntityGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.dudev.util.EntityGenerator.getCategories;
import static com.dudev.util.EntityUtil.insertEntities;
import static org.assertj.core.api.Assertions.assertThat;


class CategoryRepositoryIT extends TransactionManagementTestBase {

    static CategoryRepository categoryRepository;

    @BeforeAll
    static void repoInit() {
        categoryRepository = new CategoryRepository(session);
        session.beginTransaction();
        insertEntities(session);
        session.getTransaction().commit();
    }

    @Test
    void save() {
        Category entity = getEntity();

        categoryRepository.save(entity);

        assertThat(entity.getId()).isNotNull();
    }

    @Test
    void findById() {
        Category entity = getEntity();
        categoryRepository.save(entity);
        session.clear();

        Optional<Category> actualEntity = categoryRepository.findById(entity.getId());

        assertThat(actualEntity).isPresent();
        assertThat(actualEntity.get()).isEqualTo(entity);
    }

    @Test
    void delete() {
        Category entity = getEntity();
        categoryRepository.save(entity);
        session.clear();

        categoryRepository.delete(entity);

        assertThat(categoryRepository.findById(entity.getId())).isNotPresent();
    }

    @Test
    void update() {
        Category entity = getEntity();
        categoryRepository.save(entity);
        session.clear();

        LocalDateTime createdAt = LocalDateTime.of(2000, 1, 1, 2, 2, 1);
        entity.setCreatedAt(createdAt);
        categoryRepository.update(entity);
        session.clear();

        assertThat(categoryRepository.findById(entity.getId()).get()).isEqualTo(entity);
    }

    @Test
    void findAll() {
        List<Category> actualResult = categoryRepository.findAll();

        assertThat(actualResult.size()).isEqualTo(getEntities().size());
    }

    private List<Category> getEntities() {
        return getCategories();
    }

    private Category getEntity() {
        return EntityGenerator.getCategory();
    }
}