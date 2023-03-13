package com.dudev.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.awt.font.TextMeasurer;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class QPredicateCriteria {

    private final List<Predicate> predicates = new ArrayList<>();

    public static QPredicateCriteria builder() {
        return new QPredicateCriteria();
    }

    public <T> QPredicateCriteria add(T object, Function<T, Predicate> function) {
        if (object != null) {
            predicates.add(function.apply(object));
        }
        return this;
    }

    public Predicate[] build() {
        return predicates.toArray(Predicate[]::new);
    }
}
