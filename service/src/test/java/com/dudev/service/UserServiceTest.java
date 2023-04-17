package com.dudev.service;

import com.dudev.dto.UserCreateEditDto;
import com.dudev.dto.UserReadDto;
import com.dudev.entity.Role;
import com.dudev.entity.User;
import com.dudev.mapper.UserCreateEditMapper;
import com.dudev.mapper.UserReadMapperTest;
import com.dudev.repository.UserRepository;
import com.dudev.util.EntityGenerator;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
    private final UserReadMapperTest userReadMapper;
    @Spy
    private final UserCreateEditMapper userCreateEditMapper;

    @Test
    void findAllShouldReturnAllUsersReadDto() {
        doReturn(EntityGenerator.getUsers()).when(userRepository.findAll());

        List<UserReadDto> actualResult = userService.findAll();

        verify(userRepository, times(1));
        assertThat(actualResult.size()).isEqualTo(5);
    }

    @Test
    void findByIdShouldCallUserRepositoryAndUserReadMapper() {
        User user = EntityGenerator.getUser();
        doReturn(user).when(userRepository).findById(user.getId());

        Optional<UserReadDto> actualResult = userService.findById(user.getId());

        verify(userRepository, times(1));
        verify(userReadMapper, times(1));
    }

    @Test
    void createShouldReturnReadDtoIfUserCreated() {
        UserCreateEditDto userCreateEditDto = new UserCreateEditDto("Ivan Ivanov", "Mark123", Role.USER,
                "234234", "pass", "addr", null);
        User user = User.builder()
                .id(1)
                .phoneNumber("234234")
                .fullName("Ivan Ivanov")
                .password("pass")
                .address("addr")
                .role(Role.USER)
                .build();
        doReturn(user).when(userRepository).save(user);

        UserReadDto actualResult = userService.create(userCreateEditDto);

        verify(userRepository, times(1));
        verify(userReadMapper, times(1));
        verify(userCreateEditMapper, times(1));
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
        doReturn(user).when(userRepository).save(user);

        Assertions.assertThrows(Exception.class, () -> userService.create(null));
    }

    @Test
    void deleteShouldReturnFalseIfUserDoesNotExists() {
        int dummyId = 100500;
        doReturn(Optional.empty()).when(userRepository).findById(dummyId);

        boolean isDeleted = userService.delete(dummyId);

        assertThat(isDeleted).isFalse();
    }

    @Test
    void deleteShouldReturnTrueIfUserExists() {
        User user = EntityGenerator.getUser();
        doReturn(Optional.empty()).when(userRepository).findById(user.getId());
        doNothing().when(userRepository).delete(user);

        boolean isDeleted = userService.delete(user.getId());

        assertThat(isDeleted).isTrue();
    }
}
