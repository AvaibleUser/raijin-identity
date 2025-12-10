package edu.raijin.identity.infrastructure.adapter.in.rest.dto.user;

import jakarta.validation.constraints.NotBlank;

public record LoginUserDto(
        @NotBlank(message = "El email es requerido") String email,
        @NotBlank(message = "La contraseña es requerida") String password) {
}
