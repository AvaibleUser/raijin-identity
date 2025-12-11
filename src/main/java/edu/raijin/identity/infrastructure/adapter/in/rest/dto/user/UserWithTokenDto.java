package edu.raijin.identity.infrastructure.adapter.in.rest.dto.user;

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
        List<String> permissions) {
}
