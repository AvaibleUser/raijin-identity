package edu.raijin.identity.role.infrastructure.adapter.in.rest.dto.permission;

import java.time.Instant;

public record PermissionDto(
        Long id,
        String key,
        String description,
        Instant createdAt,
        Instant updatedAt) {
}
