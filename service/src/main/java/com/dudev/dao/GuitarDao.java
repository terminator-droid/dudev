package com.dudev.dao;

import com.dudev.dto.GuitarFilter;
import com.dudev.entity.Brand_;
import com.dudev.entity.ChangeType_;
import com.dudev.entity.Guitar;
import com.dudev.entity.Guitar_;
import com.querydsl.jpa.impl.JPAQuery;
import org.hibernate.Session;
import org.hibernate.graph.GraphSemantic;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static com.dudev.entity.QGuitar.guitar;
import static com.dudev.util.EntityGraphUtil.withBrandAndChangeType;


public class GuitarDao {

    private static final GuitarDao INSTANCE = new GuitarDao();

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

        List<Predicate> predicates = new ArrayList<>();
        if (guitarFilter.getChangeType() != null) {
            predicates.add(cb.equal(guitar.get(Guitar_.CHANGE_TYPE).get(ChangeType_.DESCRIPTION), guitarFilter.getChangeType()));
        }
        if (guitarFilter.getChangeWish() != null) {
            predicates.add(cb.like(guitar.get(Guitar_.CHANGE_WISH), guitarFilter.getChangeWish()));
        }
        if (guitarFilter.getBrand() != null) {
            predicates.add(cb.equal(guitar.get(Guitar_.BRAND).get(Brand_.NAME), guitarFilter.getBrand()));
        }
        if (guitarFilter.getYear() != 0) {
            predicates.add(cb.equal(guitar.get(Guitar_.YEAR), guitarFilter.getYear()));
        }
        if (guitarFilter.getCountry() != null) {
            predicates.add(cb.equal(guitar.get(Guitar_.COUNTRY), guitarFilter.getCountry()));
        }
        if (guitarFilter.getModel() != null) {
            predicates.add(cb.equal(guitar.get(Guitar_.MODEL), guitarFilter.getModel()));
        }

        predicates.add(cb.equal(guitar.get(Guitar_.CLOSED), guitarFilter.isClosed()));


        criteria.select(guitar)
                .where(predicates.toArray(Predicate[]::new));

        return session.createQuery(criteria).list();
    }

    public static GuitarDao getInstance() {
        return INSTANCE;
    }
}
