package com.dudev.dto;

import com.dudev.entity.Role;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Value
@FieldNameConstants
public class UserCreateEditDto {

    String fullName;
    @Email(groups = {CreateAction.class, UpdateAction.class})
    String username;
    Role role;
    String phoneNumber;

    @NotBlank(groups = {CreateAction.class})
    String rawPassword;

    String address;
    MultipartFile image;
}
