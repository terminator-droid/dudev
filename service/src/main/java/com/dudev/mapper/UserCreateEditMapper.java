package com.dudev.mapper;

import com.dudev.dto.UserCreateEditDto;
import com.dudev.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import static java.util.function.Predicate.not;

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
        if (object.getUsername() != null) {
            user.setUsername(object.getUsername());
        }
        if (object.getRole() != null) {
            user.setRole(object.getRole());
        }
        if (object.getAddress() != null) {
            user.setAddress(object.getAddress());
        }
        if (object.getPassword() != null) {
            user.setPassword(object.getPassword());
        }
        if (object.getFullName() != null) {
            user.setFullName(object.getFullName());
        }
        if (object.getPhoneNumber() != null) {
            user.setPhoneNumber(object.getPhoneNumber());
        }

        Optional.ofNullable(object.getImage())
                .filter(not(MultipartFile::isEmpty))
                .ifPresent(image -> user.setImage(image.getOriginalFilename()));
    }
}
