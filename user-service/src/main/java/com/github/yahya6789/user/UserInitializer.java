package com.github.yahya6789.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.yahya6789.user.model.User;
import com.github.yahya6789.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserInitializer implements CommandLineRunner {
    private final UserRepository repository;

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() == 0) {
            for (int i = 0; i < 55; i++) {
                String name = "user" + i;
                String email = name + "@gmail.com";
                repository.save(new User(name, email));
            }
        }
    }
}
