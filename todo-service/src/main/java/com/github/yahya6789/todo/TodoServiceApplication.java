package com.github.yahya6789.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;

import com.github.yahya6789.common.config.CommonConfig;

@SpringBootApplication
@Import(CommonConfig.class)
public class TodoServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TodoServiceApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
