package com.github.yahya6789.todo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.github.yahya6789.todo.model.Todo;
import com.github.yahya6789.todo.repository.TodoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository repository;

    public Page<Todo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Todo findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Todo ID not found: " + id));
    }

    public Todo create(Todo todo) {
        return repository.save(todo);
    }

    public Todo update(Long id, Todo updated) {
        Todo existing = findById(id);
        existing.setTitle(updated.getTitle());
        existing.setCompleted(updated.isCompleted());
        return repository.save(existing);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            new EntityNotFoundException("Todo ID not found: " + id);
        }
        repository.deleteById(id);
    }

    public Todo toggleCompleted(Long id) {
        Todo todo = findById(id);
        todo.setCompleted(!todo.isCompleted());
        return repository.save(todo);
    }
}
