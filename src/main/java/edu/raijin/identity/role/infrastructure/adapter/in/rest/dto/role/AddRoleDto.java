package edu.raijin.identity.role.infrastructure.adapter.in.rest.dto.role;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public record AddRoleDto(
        @NotBlank(message = "El nombre es requerido") String name,
        String description,
        String color,
        List<String> permissions) {
}
