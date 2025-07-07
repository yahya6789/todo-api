package com.github.yahya6789.todo.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // TODO: Replace with user from Spring Security if applicable
        return Optional.ofNullable(null);
    }
}
