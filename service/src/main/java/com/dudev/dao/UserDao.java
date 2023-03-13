package com.dudev.dao;

import com.dudev.entity.Role;
import com.dudev.entity.User;
import com.dudev.entity.User_;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.graph.GraphSemantic;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static com.dudev.util.EntityGraphUtil.withUserProductsAndBrandsAndChangeTypes;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao {

    private static final UserDao INSTANCE = new UserDao();

    public List<User> findAll(Session session) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);

        Root<User> user_root = criteria.from(User.class);
        criteria.select(user_root);
        return session.createQuery(criteria).list();
    }

    public List<User> findAllByRole(Session session, Role role) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);

        Root<User> user = criteria.from(User.class);
        criteria.select(user).where(cb.equal(user.get(User_.ROLE), role));

        return session.createQuery(criteria).list();
    }

    public List<User> findLimitedUsersOrderedByFullName(Session session, int limit) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);

        Root<User> user = criteria.from(User.class);

        criteria.select(user)
                .orderBy(cb.asc(user.get(User_.USERNAME)));

        return session.createQuery(criteria)
                .setMaxResults(limit)
                .setHint(GraphSemantic.FETCH.getJpaHintName(), withUserProductsAndBrandsAndChangeTypes(session))
                .list();
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}
