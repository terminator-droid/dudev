package com.dudev.dao;

import com.dudev.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, Integer> {
}
