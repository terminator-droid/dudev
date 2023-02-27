package com.dudev.entity;

import com.dudev.basetest.TransactionManagementTestBase;
import org.junit.jupiter.api.Test;

import static com.dudev.util.EntityGenerator.getBrand;
import static com.dudev.util.EntityGenerator.getCategory;
import static com.dudev.util.EntityGenerator.getChangeType;
import static com.dudev.util.EntityGenerator.getPedal;
import static com.dudev.util.EntityGenerator.getUser;
import static org.assertj.core.api.Assertions.assertThat;

public class PedalIT extends TransactionManagementTestBase {

    @Test
    public void save() {
        Pedal pedal = savePedal();

        assertThat(pedal.getId()).isNotNull();
    }

    @Test
    public void get() {
        Pedal pedal = savePedal();
        session.clear();

        Pedal actualPedal = session.get(Pedal.class, pedal.getId());

        assertThat(actualPedal).isEqualTo(pedal);
    }

    @Test
    public void update() {
        Pedal initialPedal = savePedal();
        session.clear();

        initialPedal.setDescription("Nice old pedal");
        session.update(initialPedal);
        session.flush();
        session.clear();
        Pedal updatedPedal = session.get(Pedal.class, initialPedal.getId());

        assertThat(updatedPedal).isEqualTo(initialPedal);
    }

    @Test
    public void delete() {
        Pedal initialPedal = savePedal();
        session.clear();

        session.delete(initialPedal);
        session.flush();
        Pedal deletedPedal = session.get(Pedal.class, initialPedal.getId());

        assertThat(deletedPedal).isNull();
    }

    private Pedal savePedal() {
        User user = getUser();
        Category category = getCategory();
        ChangeType changeType = getChangeType();
        Brand brand = getBrand(category);
        Pedal pedal = getPedal(category, changeType, brand, user);

        session.save(category);
        session.save(brand);
        session.save(user);
        session.save(changeType);
        session.save(pedal);
        return pedal;
    }
}
