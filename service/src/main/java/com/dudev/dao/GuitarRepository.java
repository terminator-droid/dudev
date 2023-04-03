package com.dudev.dao;

import com.dudev.entity.Guitar;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GuitarRepository extends JpaRepository<Guitar, Integer>, FilterGuitarRepository {
}
