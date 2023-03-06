package com.dudev.entity;

import com.dudev.basetest.TransactionManagementTestBase;
import org.junit.jupiter.api.Test;

import static com.dudev.util.EntityGenerator.getChangeType;
import static org.assertj.core.api.Assertions.assertThat;

public class ChangeTypeIT extends TransactionManagementTestBase {

    @Test
    public void save() {
        ChangeType changeType = getChangeType();

        session.save(changeType);

        assertThat(changeType.getId()).isNotNull();
    }

    @Test
    public void get() {
        ChangeType changeType = getChangeType();

        session.save(changeType);
        session.clear();
        ChangeType actualChangeType = session.get(ChangeType.class, changeType.getId());

        assertThat(actualChangeType).isEqualTo(changeType);
    }

    @Test
    public void update() {
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
    public void delete() {
        ChangeType initialChangeType = getChangeType();

        session.save(initialChangeType);
        session.clear();

        session.delete(initialChangeType);
        ChangeType deletedChangeType = session.get(ChangeType.class, initialChangeType.getId());

        assertThat(deletedChangeType).isNull();
    }
}
