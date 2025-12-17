package edu.raijin.identity.role.infrastructure.adapter.in.rest.controller;

import static org.springframework.http.HttpStatus.CREATED;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.identity.role.domain.model.Role;
import edu.raijin.identity.role.domain.usecase.CreateRoleUseCase;
import edu.raijin.identity.role.domain.usecase.DeleteRoleUseCase;
import edu.raijin.identity.role.domain.usecase.FetchRolesUseCase;
import edu.raijin.identity.role.domain.usecase.UpdateRoleUseCase;
import edu.raijin.identity.role.infrastructure.adapter.in.rest.dto.role.AddRoleDto;
import edu.raijin.identity.role.infrastructure.adapter.in.rest.dto.role.RoleDto;
import edu.raijin.identity.role.infrastructure.adapter.in.rest.dto.role.UpdateRoleDto;
import edu.raijin.identity.role.infrastructure.adapter.in.rest.mapper.RoleDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Adapter
@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final FetchRolesUseCase fetch;
    private final CreateRoleUseCase create;
    private final UpdateRoleUseCase update;
    private final DeleteRoleUseCase delete;
    private final RoleDtoMapper mapper;

    @GetMapping
    public Paged<RoleDto> fetchAll(Pageable pageable) {
        Paged<Role> roles = fetch.fetchAll(pageable);
        return roles.map(mapper::toDto);
    }

    @GetMapping("/{id}")
    public RoleDto fetch(@PathVariable Long id) {
        Role role = fetch.fetchById(id);
        return mapper.toDto(role);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public RoleDto create(@RequestBody @Valid AddRoleDto role) {
        Role result = create.create(mapper.toDomain(role));
        return mapper.toDto(result);
    }

    @PutMapping("/{id}")
    public RoleDto update(@PathVariable Long id, @RequestBody UpdateRoleDto role) {
        Role result = update.update(id, mapper.toDomain(role));
        return mapper.toDto(result);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        delete.delete(id);
    }
}
