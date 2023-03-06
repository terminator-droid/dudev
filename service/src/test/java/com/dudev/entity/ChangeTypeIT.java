package com.dudev.entity;

import com.dudev.basetest.TransactionManagementTestBase;
import org.junit.jupiter.api.Test;

import static com.dudev.util.EntityGenerator.getChangeType;
import static org.assertj.core.api.Assertions.assertThat;

class ChangeTypeIT extends TransactionManagementTestBase {

    @Test
    void save() {
        ChangeType changeType = getChangeType();

        session.save(changeType);

        assertThat(changeType.getId()).isNotNull();
    }

    @Test
    void get() {
        ChangeType changeType = getChangeType();

        session.save(changeType);
        session.clear();
        ChangeType actualChangeType = session.get(ChangeType.class, changeType.getId());

        assertThat(actualChangeType).isEqualTo(changeType);
    }

    @Test
    void update() {
        ChangeType initialChangeType = getChangeType();

        session.save(initialChangeType);
        session.clear();
        initialChangeType.setDescription("Change");
        session.update(initialChangeType);
        session.flush();
        session.clear();

        ChangeType updatedChangeType = session.get(ChangeType.class, initialChangeType.getId());

        assertThat(updatedChangeType).isEqualTo(initialChangeType);
    }

    @Test
    void delete() {
        ChangeType initialChangeType = getChangeType();

        session.save(initialChangeType);
        session.clear();

        session.delete(initialChangeType);
        ChangeType deletedChangeType = session.get(ChangeType.class, initialChangeType.getId());

        assertThat(deletedChangeType).isNull();
    }
}
