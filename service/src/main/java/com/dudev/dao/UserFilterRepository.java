package com.dudev.dao;

import com.dudev.entity.Role;
import com.dudev.entity.User;

import java.util.List;

public interface UserFilterRepository {

    List<User> findAllByRole(Role role);

    List<User> findLimitedUsersOrderedByFullName(Integer limit);
}
