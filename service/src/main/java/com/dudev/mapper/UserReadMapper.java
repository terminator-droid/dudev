package com.dudev.mapper;

import com.dudev.dto.UserReadDto;
import com.dudev.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserReadMapper implements Mapper<User, UserReadDto> {

    @Override
    public UserReadDto map(User object) {
        return new UserReadDto(
                object.getId(),
                object.getFullName(),
                object.getUsername(),
                object.getRole(),
                object.getPhoneNumber(),
                object.getAddress(),
                object.getImage());
    }
}
