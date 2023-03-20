package com.dudev.configuration;


import com.dudev.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


import javax.persistence.EntityManager;
import java.lang.reflect.Proxy;

@Configuration
@ComponentScan(basePackages = "com.dudev.dao")
public class RepositoryConfiguration {

    @Bean
    org.hibernate.cfg.Configuration configuration() {
        return HibernateUtil.buildConfiguration();
    }

    @Bean
    SessionFactory sessionFactory(org.hibernate.cfg.Configuration configuration) {
        return configuration.buildSessionFactory();
    }

    @Bean
    EntityManager entityManager(SessionFactory sessionFactory) {
        return (EntityManager) Proxy.newProxyInstance(sessionFactory.getClass().getClassLoader(), new Class[]{Session.class},
                (proxy, method, args) -> method.invoke(sessionFactory.getCurrentSession(), args));
    }
}
