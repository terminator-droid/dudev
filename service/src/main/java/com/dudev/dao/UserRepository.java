package com.dudev.dao;

import com.dudev.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>, UserFilterRepository {
}
