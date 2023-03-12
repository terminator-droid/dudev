package com.dudev.dao;

import com.dudev.dto.GuitarFilter;
import com.dudev.entity.Brand_;
import com.dudev.entity.ChangeType_;
import com.dudev.entity.Guitar;
import com.dudev.entity.Guitar_;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.Session;
import org.hibernate.graph.GraphSemantic;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static com.dudev.entity.QGuitar.guitar;
import static com.dudev.util.EntityGraphUtil.withBrandAndChangeType;


public class GuitarRepository extends RepositoryBase<Integer, Guitar> {


    public GuitarRepository(EntityManager entityManager) {
        super(entityManager, Guitar.class);
    }

    public List<Guitar> findAllGuitarsByPredicatesQueryDsl(Session session, GuitarFilter filter) {
        com.querydsl.core.types.Predicate predicate = QPredicate.builder()
                .add(filter.getModel(), guitar.model::eq)
                .add(filter.getCountry(), guitar.country::eq)
                .add(filter.getYear(), guitar.year::eq)
                .add(filter.getBrand(), guitar.brand.name::eq)
                .add(filter.getChangeWish(), guitar.changeWish::eq)
                .add(filter.getChangeType(), guitar.changeType.description::eq)
                .buildAnd();
        return new JPAQuery<Guitar>(session)
                .from(guitar)
                .where(predicate)
                .setHint(GraphSemantic.FETCH.getJpaHintName(), withBrandAndChangeType(session))
                .fetch();
    }

    public List<Guitar> findAllGuitarsQueryDsl(Session session) {
        return new JPAQuery<Guitar>(session)
                .select(guitar)
                .from(guitar)
                .fetch();
    }

    public List<Guitar> findAllGuitarsCriteria(Session session) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Guitar> criteria = cb.createQuery(Guitar.class);

        Root<Guitar> guitar = criteria.from(Guitar.class);

        criteria.select(guitar);

        return session.createQuery(criteria).list();
    }

    public List<Guitar> findGuitarsByPredicatesCriteria(Session session, GuitarFilter guitarFilter) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
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

        return session.createQuery(criteria).list();
    }
}