package com.github.yahya6789.todo.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.github.yahya6789.common.api.ApiResponse;
import com.github.yahya6789.common.dto.UserDto;

@FeignClient(name = "user-service", url = "${user.service.url}")
public interface UserServiceClient {
    @GetMapping("/{id}")
    ApiResponse<UserDto> findUserById(@PathVariable("id") long id);
}
