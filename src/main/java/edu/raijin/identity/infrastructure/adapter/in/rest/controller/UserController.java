package edu.raijin.identity.infrastructure.adapter.in.rest.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.identity.domain.model.ComplementUser;
import edu.raijin.identity.domain.usecase.ConfirmUserUseCase;
import edu.raijin.identity.domain.usecase.LoginUserUseCase;
import edu.raijin.identity.domain.usecase.RegisterUserUseCase;
import edu.raijin.identity.infrastructure.adapter.in.rest.dto.user.AddUserDto;
import edu.raijin.identity.infrastructure.adapter.in.rest.dto.user.ConfirmUserDto;
import edu.raijin.identity.infrastructure.adapter.in.rest.dto.user.LoginUserDto;
import edu.raijin.identity.infrastructure.adapter.in.rest.dto.user.UserIdDto;
import edu.raijin.identity.infrastructure.adapter.in.rest.dto.user.UserWithTokenDto;
import edu.raijin.identity.infrastructure.adapter.in.rest.mapper.UserDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Adapter
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final LoginUserUseCase login;
    private final RegisterUserUseCase register;
    private final ConfirmUserUseCase confirm;
    private final UserDtoMapper mapper;

    @PostMapping("/sign-in")
    @ResponseStatus(CREATED)
    public UserWithTokenDto login(@RequestBody @Valid LoginUserDto user) {
        ComplementUser userLogged = login.login(user.email(), user.password());

        return mapper.toDto(userLogged.getUser(), userLogged.getToken(), userLogged.getRole().getName(),
                userLogged.getPermissions());
    }

    @PostMapping("/sign-up")
    @ResponseStatus(CREATED)
    public UserIdDto register(@RequestBody @Valid AddUserDto user) {
        UUID userId = register.register(mapper.toDomain(user));

        return new UserIdDto(userId);
    }

    @PutMapping("/sign-up")
    public UserWithTokenDto confirmAccount(@RequestBody @Valid ConfirmUserDto user) {
        ComplementUser userLogged = confirm.confirm(user.code(), user.email());

        return mapper.toDto(userLogged.getUser(), userLogged.getToken(), userLogged.getRole().getName(),
                userLogged.getPermissions());
    }
}
