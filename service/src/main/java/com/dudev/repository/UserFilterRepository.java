package com.dudev.repository;

import com.dudev.dto.UserFilter;
import com.dudev.entity.Role;
import com.dudev.entity.User;

import java.util.List;

public interface UserFilterRepository {

    List<User> findAllByRole(Role role);

    List<User> findAll(UserFilter userFilter);

    List<User> findLimitedUsersOrderedByFullName(Integer limit);
}
