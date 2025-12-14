package edu.raijin.identity.user.infrastructure.adapter.in.rest.dto.user;

public record UpdateUserDto(
        String firstName,
        String lastName,
        String password) {
}
