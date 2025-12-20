package edu.raijin.identity.user.infrastructure.adapter.in.rest.dto.user;

import java.util.List;
import java.util.UUID;

public record UserWithTokenDto(
        UUID id,
        String firstName,
        String lastName,
        String dpi,
        String email,
        String token,
        String role,
        String color,
        List<String> permissions) {
}
