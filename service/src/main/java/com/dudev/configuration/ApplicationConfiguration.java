package com.dudev.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(RepositoryConfiguration.class)
public class ApplicationConfiguration {
}
