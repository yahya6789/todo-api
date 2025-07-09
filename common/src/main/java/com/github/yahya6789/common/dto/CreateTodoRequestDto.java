package com.github.yahya6789.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateTodoRequestDto {
    private String title;
    private Long userId;

    public CreateTodoRequestDto() {
    }
}
