package edu.raijin.identity.user.infrastructure.adapter.in.rest.dto.user;

import jakarta.validation.constraints.NotNull;

public record ChangeUserRoleDto(
        @NotNull(message = "El id del rol es requerido") Long roleId) {
}
