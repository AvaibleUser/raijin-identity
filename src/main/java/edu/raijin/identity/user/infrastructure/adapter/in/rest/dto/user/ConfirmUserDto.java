package edu.raijin.identity.user.infrastructure.adapter.in.rest.dto.user;

import jakarta.validation.constraints.NotBlank;

public record ConfirmUserDto(
        @NotBlank(message = "El email es requerido") String email,
        @NotBlank(message = "El código es requerido") String code) {
}
