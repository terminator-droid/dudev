package com.dudev.util;

import com.dudev.entity.Product;
import com.dudev.entity.User;
import lombok.experimental.UtilityClass;
import org.hibernate.Session;
import org.hibernate.graph.RootGraph;
import org.hibernate.graph.SubGraph;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityResult;

@UtilityClass
public class EntityGraphUtil {

    public static EntityGraph<Product> withBrandAndChangeType(EntityManager entityManager) {
        EntityGraph<Product> entityGraph = entityManager.createEntityGraph(Product.class);
        entityGraph.addAttributeNodes("brand", "changeType");
        return entityGraph;
    }

    public static RootGraph<User> withUserProductsAndBrandsAndChangeTypes(Session session) {
        RootGraph<User> entityGraph = session.createEntityGraph(User.class);
        entityGraph.addAttributeNodes("userProducts");
        SubGraph<Product> withBrandAndChangeType = entityGraph.addSubgraph("userProducts", Product.class);
        withBrandAndChangeType.addAttributeNodes("brand", "changeType");
        return entityGraph;
    }

    public static RootGraph<User> withUserProducts(Session session) {
        RootGraph<User> entityGraph = session.createEntityGraph(User.class);
        entityGraph.addAttributeNodes("userProducts");
        return entityGraph;
    }
}
