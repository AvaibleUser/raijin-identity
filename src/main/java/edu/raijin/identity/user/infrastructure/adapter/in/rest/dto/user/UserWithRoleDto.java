package edu.raijin.identity.user.infrastructure.adapter.in.rest.dto.user;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record UserWithRoleDto(
        UUID id,
        String firstName,
        String lastName,
        String dpi,
        String email,
        String roleId,
        String roleColor,
        List<String> permissions,
        Instant createdAt,
        Instant updatedAt) {
}
