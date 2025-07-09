package com.github.yahya6789.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = "com.github.yahya6789")
public class TodoServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TodoServiceApplication.class, args);
    }
}
