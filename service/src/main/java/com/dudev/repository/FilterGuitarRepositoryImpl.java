package com.dudev.repository;

import com.dudev.dto.GuitarFilter;
import com.dudev.entity.Brand_;
import com.dudev.entity.ChangeType_;
import com.dudev.entity.Guitar;
import com.dudev.entity.Guitar_;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.hibernate.graph.GraphSemantic;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

import static com.dudev.entity.QGuitar.guitar;
import static com.dudev.util.EntityGraphUtil.withBrandAndChangeType;

@RequiredArgsConstructor
public class FilterGuitarRepositoryImpl implements FilterGuitarRepository {

    private final EntityManager entityManager;

    public List<Guitar> findAllGuitarsByPredicatesQueryDsl(GuitarFilter filter) {
        com.querydsl.core.types.Predicate predicate = QPredicate.builder()
                .add(filter.getModel(), guitar.model::eq)
                .add(filter.getCountry(), guitar.country::eq)
                .add(filter.getYear(), guitar.year::eq)
                .add(filter.getBrand(), guitar.brand.name::eq)
                .add(filter.getChangeWish(), guitar.changeWish::eq)
                .add(filter.getChangeType(), guitar.changeType.description::eq)
                .buildAnd();
        return new JPAQuery<Guitar>(entityManager)
                .from(guitar)
                .where(predicate)
                .setHint(GraphSemantic.FETCH.getJpaHintName(), withBrandAndChangeType(entityManager))
                .fetch();
    }

    public List<Guitar> findGuitarsByPredicatesCriteria(GuitarFilter guitarFilter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Guitar> criteria = cb.createQuery(Guitar.class);
        Root<Guitar> guitar = criteria.from(Guitar.class);

        Predicate[] predicates = QPredicateCriteria.builder()
                .add(guitar.get(Guitar_.CHANGE_TYPE).get(ChangeType_.DESCRIPTION), it -> cb.equal(it, guitarFilter.getChangeType()))
                .add(guitar.get(Guitar_.BRAND).get(Brand_.NAME), it -> cb.equal(it, guitarFilter.getBrand()))
                .add(guitar.get(Guitar_.CHANGE_WISH), it -> cb.equal(it, guitarFilter.getChangeWish()))
                .add(guitar.get(Guitar_.YEAR), it -> cb.equal(it, guitarFilter.getYear()))
                .add(guitar.get(Guitar_.COUNTRY), it -> cb.equal(it, guitarFilter.getCountry()))
                .add(guitar.get(Guitar_.MODEL), it -> cb.equal(it, guitarFilter.getModel()))
                .add(guitar.get(Guitar_.CLOSED), it -> cb.equal(it, guitarFilter.getClosed()))
                .build();

        criteria.select(guitar)
                .where(predicates);
        return entityManager.createQuery(criteria).getResultList();
    }
}
