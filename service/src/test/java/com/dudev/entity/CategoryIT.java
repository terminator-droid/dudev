package com.dudev.entity;

import com.dudev.basetest.TransactionManagementTestBase;
import org.junit.jupiter.api.Test;


import static com.dudev.util.EntityGenerator.getCategory;
import static org.assertj.core.api.Assertions.assertThat;

public class CategoryIT extends TransactionManagementTestBase {

    @Test
    public void save() {
        Category category = getCategory();

        session.save(category);

        assertThat(category.getId()).isNotNull();
    }

    @Test
    public void get() {
        Category category = getCategory();

        session.save(category);
        session.clear();
        Category actualCategory = session.get(Category.class, category.getId());

        assertThat(actualCategory).isEqualTo(category);
    }

    @Test
    public void update() {
        Category initialCategory = getCategory();

        session.save(initialCategory);
        session.clear();
        initialCategory.setName("pedal");
        session.update(initialCategory);
        session.flush();
        session.clear();

        Category updatedCategory = session.get(Category.class, initialCategory.getId());

        assertThat(updatedCategory).isEqualTo(initialCategory);
    }

    @Test
    public void delete() {
        Category initialCategory = getCategory();

        session.save(initialCategory);
        session.clear();

        session.delete(initialCategory);
        Category deletedCategory = session.get(Category.class, initialCategory.getId());

        assertThat(deletedCategory).isNull();
    }
}
