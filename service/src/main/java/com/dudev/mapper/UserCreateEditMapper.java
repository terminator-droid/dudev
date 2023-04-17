package com.dudev.mapper;

import com.dudev.dto.UserCreateEditDto;
import com.dudev.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserCreateEditMapper implements Mapper<UserCreateEditDto, User> {

    @Override
    public User map(UserCreateEditDto fromObject, User toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    @Override
    public User map(UserCreateEditDto object) {
        User user = new User();
        copy(object, user);
        return user;
    }

    private static void copy(UserCreateEditDto object, User user) {
        user.setUsername(object.getUsername());
        user.setRole(object.getRole());
        user.setAddress(object.getAddress());
        user.setPassword(object.getPassword());
        user.setFullName(object.getFullName());
        user.setPhoneNumber(object.getPhoneNumber());
    }
}
