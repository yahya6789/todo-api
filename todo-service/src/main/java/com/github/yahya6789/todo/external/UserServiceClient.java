package com.github.yahya6789.todo.external;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.github.yahya6789.common.api.ApiResponse;
import com.github.yahya6789.common.dto.UserDto;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserServiceClient {
    private final RestTemplate restTemplate;

    @Value("${user.service.url}")
    private String userServiceUrl;

    public UserDto findUserById(long id) {
        try {
            ResponseEntity<ApiResponse<UserDto>> response = restTemplate.exchange(
                    userServiceUrl + "/" + id, HttpMethod.GET, null,
                    new ParameterizedTypeReference<ApiResponse<UserDto>>() {
                    });
            return response.getBody().getData();
        } catch (HttpClientErrorException.NotFound e) {
            throw new EntityNotFoundException("User not found", e);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
