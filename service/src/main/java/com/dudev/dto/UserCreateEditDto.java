package com.dudev.dto;

import com.dudev.entity.Role;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.web.multipart.MultipartFile;

@Value
@FieldNameConstants
public class UserCreateEditDto {

    String fullName;
    String username;
    Role role;
    String phoneNumber;
    String password;
    String address;
    MultipartFile image;
}
