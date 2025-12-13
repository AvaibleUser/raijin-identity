package edu.raijin.identity.role.infrastructure.adapter.in.rest.dto.role;

import java.util.List;

public record UpdateRoleDto(
        String description,
        String color,
        List<String> permissions) {
}
