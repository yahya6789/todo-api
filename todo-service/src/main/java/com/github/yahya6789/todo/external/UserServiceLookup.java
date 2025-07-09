package com.github.yahya6789.todo.external;

import org.springframework.stereotype.Service;

import com.github.yahya6789.common.api.ApiResponse;
import com.github.yahya6789.common.dto.UserDto;

import feign.FeignException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceLookup {
    private final UserServiceClient userServiceClient;

    public UserDto findUserById(long userId) {
        try {
            ApiResponse<UserDto> response = userServiceClient.findUserById(userId);
            if (response == null || response.getData() == null) {
                throw new EntityNotFoundException("User not found");
            }
            return response.getData();
        } catch (FeignException.NotFound e) {
            throw new EntityNotFoundException("User not found", e);
        }
    }
}
