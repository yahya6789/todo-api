package com.github.yahya6789.todo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.yahya6789.todo.model.Todo;
import com.github.yahya6789.todo.repository.TodoRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TodoInitializer implements CommandLineRunner {

    private final TodoRepository repository;

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() == 0) {
            repository.save(new Todo("Learn Spring Boot"));
            repository.save(new Todo("Build a Todo API", true));
            repository.save(new Todo("Explore HATEOAS"));
        }
    }
}
