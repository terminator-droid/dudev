package com.dudev.service;

import com.dudev.dto.UserCreateEditDto;
import com.dudev.dto.UserReadDto;
import com.dudev.entity.Role;
import com.dudev.entity.User;
import com.dudev.mapper.UserCreateEditMapper;
import com.dudev.mapper.UserReadMapper;
import com.dudev.repository.UserRepository;
import com.dudev.util.EntityGenerator;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RequiredArgsConstructor
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Autowired
    private final UserService userService;
    @Mock
    private final UserRepository userRepository;
    @Spy
    private final UserReadMapper userReadMapper;
    @Spy
    private final UserCreateEditMapper userCreateEditMapper;

    @Test
    void findAllShouldReturnAllUsersReadDto() {
        Mockito.doReturn(EntityGenerator.getUsers()).when(userRepository.findAll());

        List<UserReadDto> actualResult = userService.findAll();

        Mockito.verify(userRepository, Mockito.times(1));
        assertThat(actualResult.size()).isEqualTo(5);
    }

    @Test
    void findByIdShouldCallUserRepositoryAndUserReadMapper() {
        User user = EntityGenerator.getUser();
        Mockito.doReturn(user).when(userRepository).findById(user.getId());

        Optional<UserReadDto> actualResult = userService.findById(user.getId());

        Mockito.verify(userRepository, Mockito.times(1));
        Mockito.verify(userReadMapper, Mockito.times(1));
    }

    @Test
    void createShouldReturnReadDtoIfUserCreated() {
        UserCreateEditDto userCreateEditDto = new UserCreateEditDto("Ivan Ivanov", "Mark123", Role.USER,
                "234234", "pass", "addr");
        User user = User.builder()
                .id(1)
                .phoneNumber("234234")
                .fullName("Ivan Ivanov")
                .password("pass")
                .address("addr")
                .role(Role.USER)
                .build();
        Mockito.doReturn(user).when(userRepository).save(user);

        UserReadDto actualResult = userService.create(userCreateEditDto);

        Mockito.verify(userRepository, Mockito.times(1));
        Mockito.verify(userReadMapper, Mockito.times(1));
        Mockito.verify(userCreateEditMapper, Mockito.times(1));
    }

    @Test
    void createShouldThrowException() {
        User user = User.builder()
                .id(1)
                .phoneNumber("234234")
                .fullName("Ivan Ivanov")
                .password("pass")
                .address("addr")
                .role(Role.USER)
                .build();
        Mockito.doReturn(user).when(userRepository).save(user);

        Assertions.assertThrows(Exception.class, () -> userService.create(null));
    }

    @Test
    void deleteShouldReturnFalseIfUserDoesNotExists() {
        int dummyId = 100500;
        Mockito.doReturn(Optional.empty()).when(userRepository).findById(dummyId);

        boolean isDeleted = userService.delete(dummyId);

        assertThat(isDeleted).isFalse();
    }

    @Test
    void deleteShouldReturnTrueIfUserExists() {
        User user = EntityGenerator.getUser();
        Mockito.doReturn(Optional.empty()).when(userRepository).findById(user.getId());
        Mockito.doNothing().when(userRepository).delete(user);

        boolean isDeleted = userService.delete(user.getId());

        assertThat(isDeleted).isTrue();
    }
}
