package edu.raijin.identity.user.infrastructure.adapter.in.rest.dto.user;

import java.time.Instant;
import java.util.UUID;

public record UserWithRoleDto(
        UUID id,
        String firstName,
        String lastName,
        String dpi,
        String email,
        String roleId,
        String roleColor,
        Instant createdAt,
        Instant updatedAt) {
}
