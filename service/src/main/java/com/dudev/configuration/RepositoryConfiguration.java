package com.dudev.configuration;


import com.dudev.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import java.lang.reflect.Proxy;

@Configuration
@ComponentScan(basePackages = "com.dudev.dao")
public class RepositoryConfiguration {
}
