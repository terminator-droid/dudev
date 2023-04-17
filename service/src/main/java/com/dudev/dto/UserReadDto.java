package com.dudev.dto;

import com.dudev.entity.Role;
import lombok.Value;

@Value
public class UserReadDto {

    Integer id;
    String fullName;
    String username;
    Role role;
    String phoneNumber;
    String address;
    String image;
}
