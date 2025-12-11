package edu.raijin.identity.infrastructure.adapter.in.rest.dto.user;

import jakarta.validation.constraints.NotBlank;

public record AddUserDto(
        @NotBlank(message = "El nombre es requerido") String firstName,
        @NotBlank(message = "El apellido es requerido") String lastName,
        @NotBlank(message = "El DPI es requerido") String dpi,
        @NotBlank(message = "El email es requerido") String email,
        @NotBlank(message = "La contraseña es requerido") String password) {
}
