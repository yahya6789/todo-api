package com.github.yahya6789.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {
    private long id;
    private String name;
    private String email;

    public UserDto() {
    }
}
