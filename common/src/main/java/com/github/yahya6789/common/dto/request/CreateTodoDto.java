package com.github.yahya6789.common.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateTodoDto {
    private String title;
    private long userId;

    public CreateTodoDto() {
    }
}
