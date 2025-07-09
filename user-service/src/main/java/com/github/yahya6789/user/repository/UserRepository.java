package com.github.yahya6789.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.yahya6789.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
