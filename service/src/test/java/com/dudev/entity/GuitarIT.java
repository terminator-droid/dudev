package com.dudev.entity;

import com.dudev.basetest.TransactionManagementTestBase;
import org.junit.jupiter.api.Test;

import static com.dudev.util.EntityGenerator.getBrand;
import static com.dudev.util.EntityGenerator.getCategory;
import static com.dudev.util.EntityGenerator.getChangeType;
import static com.dudev.util.EntityGenerator.getGuitar;
import static com.dudev.util.EntityGenerator.getUser;
import static org.assertj.core.api.Assertions.assertThat;

class GuitarIT extends TransactionManagementTestBase {

    @Test
    void save() {
        User user = getUser();
        Category category = getCategory();
        ChangeType changeType = getChangeType();
        Brand brand = getBrand(category);
        Guitar guitar = getGuitar(category, changeType, brand, user);

        session.save(category);
        session.save(brand);
        session.save(user);
        session.save(changeType);
        session.save(guitar);

        assertThat(guitar.getId()).isNotNull();
    }

    @Test
    void get() {
        Guitar guitar = saveGuitar();
        session.clear();
        Guitar actualGuitar = session.get(Guitar.class, guitar.getId());

        assertThat(actualGuitar).isEqualTo(guitar);
    }

    @Test
    void update() {
        Guitar initialGuitar = saveGuitar();
        session.clear();

        initialGuitar.setCountry("Japan");
        session.update(initialGuitar);
        session.flush();
        session.clear();
        Guitar updatedGuitar = session.get(Guitar.class, initialGuitar.getId());

        assertThat(updatedGuitar).isEqualTo(initialGuitar);
    }

    @Test
    void delete() {
        Guitar initialGuitar = saveGuitar();
        session.clear();

        session.delete(initialGuitar);
        session.flush();
        Guitar deletedGuitar = session.get(Guitar.class, initialGuitar.getId());

        assertThat(deletedGuitar).isNull();
    }

    Guitar saveGuitar() {
        User user = getUser();
        Category category = getCategory();
        ChangeType changeType = getChangeType();
        Brand brand = getBrand(category);
        Guitar guitar = getGuitar(category, changeType, brand, user);

        session.save(category);
        session.save(brand);
        session.save(user);
        session.save(changeType);
        session.save(guitar);
        return guitar;
    }
}
