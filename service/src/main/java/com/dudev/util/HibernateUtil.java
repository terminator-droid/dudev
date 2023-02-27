package com.dudev.util;

import com.dudev.entity.Brand;
import com.dudev.entity.Category;
import com.dudev.entity.ChangeType;
import com.dudev.entity.Guitar;
import com.dudev.entity.Offer;
import com.dudev.entity.OfferProduct;
import com.dudev.entity.Pedal;
import com.dudev.entity.Product;
import com.dudev.entity.Role;
import com.dudev.entity.User;
import com.dudev.entity.UserLikedProduct;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateUtil {

    public static SessionFactory buildSessionFactory() {
        Configuration configuration = buildConfiguration();
        configuration.configure();
        return configuration.buildSessionFactory();
    }

    public static Configuration buildConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Guitar.class);
        configuration.addAnnotatedClass(Pedal.class);
        configuration.addAnnotatedClass(Brand.class);
        configuration.addAnnotatedClass(Category.class);
        configuration.addAnnotatedClass(Offer.class);
        configuration.addAnnotatedClass(ChangeType.class);
        configuration.addAnnotatedClass(Role.class);
        configuration.addAnnotatedClass(Product.class);
        configuration.addAnnotatedClass(UserLikedProduct.class);
        configuration.addAnnotatedClass(OfferProduct.class);
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        return configuration;
    }
}
