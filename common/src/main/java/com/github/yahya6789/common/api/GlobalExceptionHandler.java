package com.github.yahya6789.common.api;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidation(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(
                error -> errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.badRequest()
                .body(ResponseFactory.failure("Validation failed", errors));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleEntityNotFound(EntityNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ResponseFactory.failure(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGeneric(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseFactory.failure(e.getMessage()));
    }
}
