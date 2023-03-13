package com.dudev.util;

import com.dudev.entity.Brand;
import com.dudev.entity.Category;
import com.dudev.entity.ChangeType;
import com.dudev.entity.Product;
import com.dudev.entity.User;
import lombok.experimental.UtilityClass;
import org.hibernate.Session;

import java.util.List;

import static com.dudev.util.EntityGenerator.getBrands;
import static com.dudev.util.EntityGenerator.getCategories;
import static com.dudev.util.EntityGenerator.getChangeTypes;
import static com.dudev.util.EntityGenerator.getProducts;
import static com.dudev.util.EntityGenerator.getUsers;


@UtilityClass
public class EntityUtil {

    public static void insertEntities(Session session) {
        List<Category> categories = insertCategories(session);
        List<Brand> brands = insertBrands(session, categories);
        List<ChangeType> changeTypes = insertChangeTypes(session);
        List<User> users = insertUsers(session);
        insertProducts(session, users, changeTypes, categories, brands);
    }


    private static void insertProducts(Session session, List<User> users, List<ChangeType> changeTypes,
                                       List<Category> categories,
                                       List<Brand> brands) {
        List<Product> products = getProducts(users, changeTypes, categories, brands);
        for (Product product : products) {
            session.save(product);
        }
        session.flush();
    }

    private static List<User> insertUsers(Session session) {
        List<User> users = getUsers();
        for (User user : users) {
            session.save(user);
        }
        session.flush();
        return users;
    }


    private static List<ChangeType> insertChangeTypes(Session session) {
        List<ChangeType> changeTypes = getChangeTypes();
        for (ChangeType changeType : changeTypes) {
            session.save(changeType);
        }
        session.flush();
        return changeTypes;
    }

    private static List<Brand> insertBrands(Session session, List<Category> categories) {
        List<Brand> brands = getBrands(categories);
        for (Brand brand : brands) {
            session.save(brand);
        }
        session.flush();
        return brands;
    }

    private static List<Category> insertCategories(Session session) {
        List<Category> categories = getCategories();

        for (Category category : categories) {
            session.save(category);
        }
        session.flush();
        return categories;
    }
}
