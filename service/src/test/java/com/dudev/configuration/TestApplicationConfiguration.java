package com.dudev.configuration;

import com.dudev.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(TestRepositoryConfiguration.class)
public class TestApplicationConfiguration {
}
