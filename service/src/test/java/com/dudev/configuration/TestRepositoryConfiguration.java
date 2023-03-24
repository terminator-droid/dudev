package com.dudev.configuration;

import com.dudev.util.HibernateTestUtil;
import com.dudev.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@Import(RepositoryConfiguration.class)
public class TestRepositoryConfiguration {

    @Bean
    SessionFactory sessionFactory() {
        return HibernateTestUtil.buildSessionFactory();
    }
}
