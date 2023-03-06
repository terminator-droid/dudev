package com.dudev.entity;

import com.dudev.basetest.TransactionManagementTestBase;
import org.junit.jupiter.api.Test;

import static com.dudev.util.EntityGenerator.getUser;
import static org.assertj.core.api.Assertions.assertThat;

public class UserIT extends TransactionManagementTestBase {

    @Test
    public void save() {
        User user = saveUser();

        assertThat(user.getId()).isNotNull();
    }

    @Test
    public void get() {
        User user = saveUser();
        session.clear();
        User actualUser = session.get(User.class, user.getId());

        assertThat(actualUser).isEqualTo(user);
    }

    @Test
    public void update() {
        User initialUser = saveUser();
        session.clear();
        initialUser.setUsername("TestUsername_123");
        session.update(initialUser);
        session.flush();
        session.clear();

        User updatedUser = session.get(User.class, initialUser.getId());

        assertThat(updatedUser).isEqualTo(initialUser);
    }

    @Test
    public void delete() {
        User user = saveUser();
        session.clear();

        session.delete(user);
        User deletedUser = session.get(User.class, user.getId());

        assertThat(deletedUser).isNull();
    }

    private User saveUser() {
        User user = getUser();

        session.save(user);
        return user;
    }
}
