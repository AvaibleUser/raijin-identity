package edu.raijin.identity.role.infrastructure.adapter.in.rest.dto.role;

import java.time.Instant;
import java.util.List;

public record RoleDto(
        Long id,
        String name,
        String description,
        String color,
        List<Long> permissions,
        Instant createdAt,
        Instant updatedAt) {
}
