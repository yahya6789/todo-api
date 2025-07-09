package com.github.yahya6789.common.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@ComponentScan(basePackages = "com.github.yahya6789.common")
@EnableJpaAuditing
public class CommonConfig {
    // You can also define shared beans here in the future
}
