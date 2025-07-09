package com.github.yahya6789.user.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.yahya6789.common.api.ApiResponse;
import com.github.yahya6789.common.api.ResponseFactory;
import com.github.yahya6789.user.model.User;
import com.github.yahya6789.user.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping
    public ApiResponse<PagedModel<EntityModel<User>>> findAll(Pageable pageable,
            PagedResourcesAssembler<User> assembler) {
        Page<User> page = service.findAll(pageable);
        PagedModel<EntityModel<User>> model = assembler.toModel(page, this::toModel);
        return ResponseFactory.success("Users fetched", model);
    }

    @GetMapping("/{id}")
    public ApiResponse<EntityModel<User>> findById(@PathVariable Long id) {
        return ResponseFactory.success("User found", toModel(service.findById(id)));
    }

    @PostMapping
    public ApiResponse<EntityModel<User>> create(@Valid @RequestBody User user) {
        return ResponseFactory.success("User created", toModel(service.create(user)));
    }

    @PutMapping("/{id}")
    public ApiResponse<EntityModel<User>> update(@PathVariable Long id, @Valid @RequestBody User user) {
        return ResponseFactory.success("User updated", toModel(service.update(id, user)));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseFactory.success("User deleted", null);
    }

    private EntityModel<User> toModel(User user) {
        EntityModel<User> model = EntityModel.of(user,
                linkTo(methodOn(UserController.class).findById(user.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).findAll(null, null)).withRel("users"));

        model.add(linkTo(methodOn(UserController.class).update(user.getId(), null))
                .withRel("update")
                .withType("PUT"));
        model.add(linkTo(methodOn(UserController.class).delete(user.getId()))
                .withRel("delete")
                .withType("DELETE"));
        return model;
    }
}
