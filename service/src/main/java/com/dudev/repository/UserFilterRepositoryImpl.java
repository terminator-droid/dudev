package com.dudev.repository;

import com.dudev.entity.Role;
import com.dudev.entity.User;
import com.dudev.entity.User_;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.graph.GraphSemantic;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static com.dudev.util.EntityGraphUtil.withUserProductsAndBrandsAndChangeTypes;

@RequiredArgsConstructor
public class UserFilterRepositoryImpl implements UserFilterRepository {

    @Autowired
    private final EntityManager entityManager;

    public List<User> findAllByRole(Role role) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);

        Root<User> user = criteria.from(User.class);
        criteria.select(user).where(cb.equal(user.get(User_.ROLE), role));

        return entityManager.createQuery(criteria).getResultList();
    }

    public List<User> findLimitedUsersOrderedByFullName(Integer limit) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);

        Root<User> user = criteria.from(User.class);

        criteria.select(user)
                .orderBy(cb.asc(user.get(User_.USERNAME)));

        return entityManager.createQuery(criteria)
                .setMaxResults(limit)
                .setHint(GraphSemantic.FETCH.getJpaHintName(), withUserProductsAndBrandsAndChangeTypes((Session) entityManager))
                .getResultList();
    }
}
