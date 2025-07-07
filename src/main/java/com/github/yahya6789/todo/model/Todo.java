package com.github.yahya6789.todo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
public class Todo extends AbstractAuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotBlank
    private String title;

    private boolean completed = false;

    // Required by JPA
    protected Todo() {}

    public Todo(String title) {
        this.title = title;
    }

    public Todo(String title, boolean completed) {
        this.title = title;
        this.completed = completed;
    }
}
