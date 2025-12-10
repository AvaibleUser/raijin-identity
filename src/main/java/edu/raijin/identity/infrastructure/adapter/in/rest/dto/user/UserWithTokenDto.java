package edu.raijin.identity.infrastructure.adapter.in.rest.dto.user;

import java.util.UUID;

public record UserWithTokenDto(
        UUID id,
        String firstName,
        String lastName,
        String email,
        String token) {
}
