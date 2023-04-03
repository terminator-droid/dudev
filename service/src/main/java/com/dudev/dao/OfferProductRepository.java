package com.dudev.dao;

import com.dudev.entity.OfferProduct;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OfferProductRepository extends JpaRepository<OfferProduct, Integer> {
}
