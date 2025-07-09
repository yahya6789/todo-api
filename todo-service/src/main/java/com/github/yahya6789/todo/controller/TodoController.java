package com.github.yahya6789.todo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.yahya6789.common.api.ApiResponse;
import com.github.yahya6789.common.api.ResponseFactory;
import com.github.yahya6789.common.dto.CreateTodoRequestDto;
import com.github.yahya6789.common.dto.UserDto;
import com.github.yahya6789.todo.external.UserServiceLookup;
import com.github.yahya6789.todo.model.Todo;
import com.github.yahya6789.todo.service.TodoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService service;
    private final UserServiceLookup userServiceLookup;

    @GetMapping
    public ApiResponse<PagedModel<EntityModel<Todo>>> findAll(Pageable pageable,
            PagedResourcesAssembler<Todo> assembler) {
        Page<Todo> page = service.findAll(pageable);
        PagedModel<EntityModel<Todo>> pagedModel = assembler.toModel(page, this::toEntityModel);
        return ResponseFactory.success("Todos fetched", pagedModel);
    }

    @GetMapping("/{id}")
    public ApiResponse<EntityModel<Todo>> findById(@PathVariable Long id) {
        Todo todo = service.findById(id);
        return ResponseFactory.success("Todo found", toEntityModel(todo));
    }

    @PostMapping
    public ApiResponse<EntityModel<Todo>> create(@Valid @RequestBody CreateTodoRequestDto request) {
        UserDto userDto = userServiceLookup.findUserById(request.getUserId());
        Todo todo = service.create(new Todo(userDto.getId(), request.getTitle()));
        return ResponseFactory.success("Todo created", toEntityModel(todo));
    }

    @PutMapping("/{id}")
    public ApiResponse<EntityModel<Todo>> update(@PathVariable Long id, @Valid @RequestBody Todo updated) {
        Todo todo = service.update(id, updated);
        return ResponseFactory.success("Todo updated", toEntityModel(todo));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseFactory.success("Todo deleted", null);
    }

    @PatchMapping("/{id}/toggle-completed")
    public ApiResponse<EntityModel<Todo>> toggleComplete(@PathVariable Long id) {
        Todo todo = service.toggleCompleted(id);
        return ResponseFactory.success("Todo completion status changed", toEntityModel(todo));
    }

    private EntityModel<Todo> toEntityModel(Todo todo) {
        EntityModel<Todo> model = EntityModel.of(todo,
                linkTo(methodOn(TodoController.class).findById(todo.getId())).withSelfRel(),
                linkTo(methodOn(TodoController.class).findAll(null, null)).withRel(relFor(Todo.class)));

        model.add(linkTo(methodOn(TodoController.class).toggleComplete(todo.getId()))
                .withRel("toggle-completed")
                .withType("PUT"));

        if (!todo.isCompleted()) {
            model.add(linkTo(methodOn(TodoController.class).update(todo.getId(), null))
                    .withRel("update")
                    .withType("PUT"));
            model.add(linkTo(methodOn(TodoController.class).delete(todo.getId()))
                    .withRel("delete")
                    .withType("DELETE"));
        }
        return model;
    }

    private String relFor(Class<?> clazz) {
        return clazz.getSimpleName().toLowerCase() + "s";
    }
}
