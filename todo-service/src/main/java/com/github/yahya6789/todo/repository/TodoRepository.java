package com.github.yahya6789.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.yahya6789.todo.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
