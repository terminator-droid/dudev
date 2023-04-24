package com.dudev.service;

import com.dudev.basetest.IntegrationTestBase;
import com.dudev.dto.UserCreateEditDto;
import com.dudev.dto.UserReadDto;
import com.dudev.entity.Role;
import com.dudev.util.EntityUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
public class UserServiceIT extends IntegrationTestBase {

    private static final Integer USER_1 = 1;
    private final UserService userService;
    private final EntityManager entityManager;

    @Test
    void findAll() {
        List<UserReadDto> actualResult = userService.findAll();
        assertThat(actualResult).hasSize(5);
    }

    @Test
    void findById() {
        EntityUtil.insertEntities(entityManager);
        Optional<UserReadDto> user = userService.findById(USER_1);

        assertTrue(user.isPresent());
        user.ifPresent(it -> assertThat(it.getUsername()).isEqualTo("Transformer111"));
    }

    @Test
    void create() {
        UserCreateEditDto userCreateEditDto = new UserCreateEditDto("Ivan Ivanov", "Mark123", Role.USER,
                "234234", "pass", "addr", null);

        UserReadDto actualResult = userService.create(userCreateEditDto);

        assertEquals(userCreateEditDto.getFullName(), actualResult.getFullName());
        assertSame(userCreateEditDto.getRole(), actualResult.getRole());
        assertEquals(userCreateEditDto.getAddress(), actualResult.getAddress());
        assertEquals(userCreateEditDto.getPhoneNumber(), actualResult.getPhoneNumber());
        assertEquals(userCreateEditDto.getFullName(), actualResult.getFullName());
        assertEquals(userCreateEditDto.getUsername(), actualResult.getUsername());
    }

    @Test
    void update() {
        UserCreateEditDto userCreateEditDto = new UserCreateEditDto("Ivan Ivanov", "Mark123", Role.USER,
                "234234", "pass", "addr", null);
        Optional<UserReadDto> actualResult = userService.update(USER_1, userCreateEditDto);

        assertThat(actualResult).isPresent();
        actualResult.ifPresent(user -> {
            assertEquals(userCreateEditDto.getFullName(), user.getFullName());
            assertSame(userCreateEditDto.getRole(), user.getRole());
            assertEquals(userCreateEditDto.getAddress(), user.getAddress());
            assertEquals(userCreateEditDto.getPhoneNumber(), user.getPhoneNumber());
            assertEquals(userCreateEditDto.getFullName(), user.getFullName());
            assertEquals(userCreateEditDto.getUsername(), user.getUsername());
        });
    }

    @Test
    void delete() {
        assertTrue(userService.delete(USER_1));
        assertFalse(userService.delete(12312312));
    }
}
