package com.dudev.repository;

import com.dudev.dto.GuitarFilter;
import com.dudev.entity.Guitar;

import java.util.List;

public interface FilterGuitarRepository {

    List<Guitar> findAllGuitarsByPredicatesQueryDsl(GuitarFilter filter);

    List<Guitar> findGuitarsByPredicatesCriteria(GuitarFilter guitarFilter);
}
