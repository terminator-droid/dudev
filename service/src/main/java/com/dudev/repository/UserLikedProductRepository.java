package com.dudev.repository;

import com.dudev.entity.UserLikedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLikedProductRepository extends JpaRepository<UserLikedProduct, Integer> {
}
