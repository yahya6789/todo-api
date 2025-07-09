package com.github.yahya6789.todo.model;

import com.github.yahya6789.common.model.AuditableEntity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
public class Todo extends AuditableEntity {
    @NotBlank
    private String title;

    private boolean completed = false;

    // Required by JPA
    protected Todo() {
    }

    public Todo(String title) {
        this.title = title;
    }

    public Todo(String title, boolean completed) {
        this.title = title;
        this.completed = completed;
    }
}
