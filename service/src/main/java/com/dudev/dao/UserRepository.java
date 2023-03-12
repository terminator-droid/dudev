package com.dudev.dao;

import com.dudev.entity.Role;
import com.dudev.entity.User;
import com.dudev.entity.User_;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.graph.GraphSemantic;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static com.dudev.util.EntityGraphUtil.withUserProductsAndBrandsAndChangeTypes;

public class UserRepository extends RepositoryBase<Integer, User> {

    public UserRepository(EntityManager entityManager) {
        super(entityManager, User.class);
    }

    public List<User> findAllByRole(Role role) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);

        Root<User> user = criteria.from(User.class);
        criteria.select(user).where(cb.equal(user.get(User_.ROLE), role));

        return getEntityManager().createQuery(criteria).getResultList();
    }

    public List<User> findLimitedUsersOrderedByFullName(Integer limit) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);

        Root<User> user = criteria.from(User.class);

        criteria.select(user)
                .orderBy(cb.asc(user.get(User_.USERNAME)));

        return getEntityManager().createQuery(criteria)
                .setMaxResults(limit)
                .setHint(GraphSemantic.FETCH.getJpaHintName(), withUserProductsAndBrandsAndChangeTypes((Session) getEntityManager()))
                .getResultList();
    }
}
