package com.github.yahya6789.todo.controller;

public class ResponseFactory {
    public static <T> ApiResponse<T> success(String msg, T data) {
        return new ApiResponse<>(true, msg, data);
    }

    public static <T> ApiResponse<T> failure(String message, T data) {
        return new ApiResponse<>(false, message, data);
    }

    public static <T> ApiResponse<T> failure(String msg) {
        return new ApiResponse<>(false, msg, null);
    }
}
