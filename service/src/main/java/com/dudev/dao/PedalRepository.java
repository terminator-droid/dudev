package com.dudev.dao;

import com.dudev.entity.Pedal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedalRepository extends JpaRepository<Pedal, Integer> {
}
