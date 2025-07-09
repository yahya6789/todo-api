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
    private long userId;

    @NotBlank
    private String title;

    private boolean completed = false;

    // Required by JPA
    protected Todo() {
    }

    public Todo(long userId, String title) {
        this.userId = userId;
        this.title = title;
    }

    public Todo(long userId, String title, boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }
}
