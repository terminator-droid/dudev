package com.dudev.util;

import com.dudev.entity.Brand;
import com.dudev.entity.Category;
import com.dudev.entity.ChangeType;
import com.dudev.entity.Product;
import com.dudev.entity.User;
import lombok.experimental.UtilityClass;
import javax.persistence.EntityManager;

import javax.persistence.EntityManager;
import java.util.List;

import static com.dudev.util.EntityGenerator.getBrands;
import static com.dudev.util.EntityGenerator.getCategories;
import static com.dudev.util.EntityGenerator.getChangeTypes;
import static com.dudev.util.EntityGenerator.getProducts;
import static com.dudev.util.EntityGenerator.getUsers;


@UtilityClass
public class EntityUtil {

    public static void insertEntities(EntityManager entityManager) {
        List<Category> categories = insertCategories(entityManager);
        List<Brand> brands = insertBrands(entityManager, categories);
        List<ChangeType> changeTypes = insertChangeTypes(entityManager);
        List<User> users = insertUsers(entityManager);
        insertProducts(entityManager, users, changeTypes, categories, brands);
    }


    private static void insertProducts(EntityManager entityManager, List<User> users, List<ChangeType> changeTypes,
                                       List<Category> categories,
                                       List<Brand> brands) {
        List<Product> products = getProducts(users, changeTypes, categories, brands);
        products.forEach(entityManager::persist);
        entityManager.flush();
    }

    private static List<User> insertUsers(EntityManager entityManager) {
        List<User> users = getUsers();
        users.forEach(entityManager::persist);
        entityManager.flush();
        return users;
    }


    private static List<ChangeType> insertChangeTypes(EntityManager entityManager) {
        List<ChangeType> changeTypes = getChangeTypes();
        changeTypes.forEach(entityManager::persist);
        entityManager.flush();
        return changeTypes;
    }

    private static List<Brand> insertBrands(EntityManager entityManager, List<Category> categories) {
        List<Brand> brands = getBrands(categories);
        brands.forEach(entityManager::persist);
        entityManager.flush();
        return brands;
    }

    private static List<Category> insertCategories(EntityManager entityManager) {
        List<Category> categories = getCategories();

        categories.forEach(entityManager::persist);
        entityManager.flush();
        return categories;
    }
}
