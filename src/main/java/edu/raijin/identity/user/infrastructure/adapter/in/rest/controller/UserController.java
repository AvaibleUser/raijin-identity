package edu.raijin.identity.user.infrastructure.adapter.in.rest.controller;

import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.commons.util.annotation.CurrentUser;
import edu.raijin.identity.user.domain.model.User;
import edu.raijin.identity.user.domain.usecase.BanUserUseCase;
import edu.raijin.identity.user.domain.usecase.ChangeUserRoleUseCase;
import edu.raijin.identity.user.domain.usecase.DeleteUserUseCase;
import edu.raijin.identity.user.domain.usecase.FetchUserUseCase;
import edu.raijin.identity.user.domain.usecase.FetchUsersUseCase;
import edu.raijin.identity.user.domain.usecase.UpdateUserUseCase;
import edu.raijin.identity.user.infrastructure.adapter.in.rest.dto.user.ChangeUserRoleDto;
import edu.raijin.identity.user.infrastructure.adapter.in.rest.dto.user.UpdateUserDto;
import edu.raijin.identity.user.infrastructure.adapter.in.rest.dto.user.UserWithRoleDto;
import edu.raijin.identity.user.infrastructure.adapter.in.rest.mapper.UserDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Adapter
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final FetchUserUseCase fetchUser;
    private final FetchUsersUseCase fetchAll;
    private final UpdateUserUseCase update;
    private final BanUserUseCase ban;
    private final ChangeUserRoleUseCase changeRole;
    private final DeleteUserUseCase remove;
    private final UserDtoMapper mapper;

    @GetMapping
    public Paged<UserWithRoleDto> fetchAll(Pageable pageable) {
        return fetchAll.fetchAll(pageable).map(mapper::toDto);
    }

    @GetMapping("/me")
    public UserWithRoleDto fetchMe(@CurrentUser UUID id) {
        return mapper.toDto(fetchUser.fetchById(id));
    }

    @GetMapping("/{id}")
    public UserWithRoleDto fetchUser(@PathVariable UUID id) {
        return mapper.toDto(fetchUser.fetchById(id));
    }

    @PutMapping
    public UserWithRoleDto update(@CurrentUser UUID id, @RequestBody UpdateUserDto user) {
        User updated = update.update(id, mapper.toDomain(user));
        return mapper.toDto(updated);
    }

    @PatchMapping("/{id}/ban")
    @ResponseStatus(NO_CONTENT)
    public void ban(@PathVariable UUID id) {
        ban.ban(id);
    }

    @PatchMapping("/{id}/role")
    public void changeRole(@PathVariable UUID id, @RequestBody @Valid ChangeUserRoleDto role) {
        changeRole.changeRole(id, role.roleId());
    }

    @DeleteMapping
    @ResponseStatus(NO_CONTENT)
    public void remove(@CurrentUser UUID id) {
        remove.delete(id);
    }
}
