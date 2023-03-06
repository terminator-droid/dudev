package com.dudev.entity;

import com.dudev.basetest.TransactionManagementTestBase;
import org.junit.jupiter.api.Test;

import static com.dudev.util.EntityGenerator.getBrand;
import static com.dudev.util.EntityGenerator.getCategory;
import static org.assertj.core.api.Assertions.assertThat;

public class BrandIT extends TransactionManagementTestBase {

    @Test
    public void save() {
        Brand brand = saveBrand();

        assertThat(brand.getId()).isNotNull();
    }

    @Test
    public void get() {
        Brand brand = saveBrand();
        session.clear();
        Brand actualBrand = session.get(Brand.class, brand.getId());

        assertThat(actualBrand).isEqualTo(brand);
    }

    @Test
    public void update() {
        Brand initialBrand = saveBrand();
        session.clear();
        initialBrand.setName("Gibson");
        session.update(initialBrand);
        session.flush();
        session.clear();

        Brand updatedBrand = session.get(Brand.class, initialBrand.getId());

        assertThat(updatedBrand).isEqualTo(initialBrand);
    }

    @Test
    public void delete() {
        Brand brand = saveBrand();
        session.clear();

        session.delete(brand);
        Brand deletedBrand = session.get(Brand.class, brand.getId());

        assertThat(deletedBrand).isNull();
    }

    private Brand saveBrand() {
        Category category = getCategory();
        Brand brand = getBrand(category);

        session.save(category);
        session.save(brand);
        return brand;
    }
}
