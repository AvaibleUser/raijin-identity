package edu.raijin.identity.role.infrastructure.adapter.in.rest.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.identity.role.domain.usecase.AddRolePermissionUseCase;
import edu.raijin.identity.role.domain.usecase.FetchPermissionsUseCase;
import edu.raijin.identity.role.domain.usecase.FetchRolePermissionsUseCase;
import edu.raijin.identity.role.domain.usecase.RemoveRolePermissionUseCase;
import edu.raijin.identity.role.infrastructure.adapter.in.rest.dto.permission.AddPermissionDto;
import edu.raijin.identity.role.infrastructure.adapter.in.rest.dto.permission.PermissionDto;
import edu.raijin.identity.role.infrastructure.adapter.in.rest.mapper.PermissionDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Adapter
@RestController
@RequiredArgsConstructor
public class PermissionController {

    private final FetchPermissionsUseCase findPermissions;
    private final FetchRolePermissionsUseCase findRolePermissions;
    private final AddRolePermissionUseCase addPermission;
    private final RemoveRolePermissionUseCase removePermission;
    private final PermissionDtoMapper mapper;

    @GetMapping("/permissions")
    public List<PermissionDto> fetchAll() {
        return mapper.toDto(findPermissions.findAll());
    }

    @GetMapping("/roles/{roleId}/permissions")
    public List<PermissionDto> fetchAllByRoleId(@PathVariable Long roleId) {
        return mapper.toDto(findRolePermissions.findAllRolePermissions(roleId));
    }

    @PostMapping("/roles/{roleId}/permissions")
    @ResponseStatus(CREATED)
    public void addPermission(@PathVariable Long roleId, @RequestBody @Valid AddPermissionDto permission) {
        addPermission.addPermission(mapper.toDomain(permission, roleId));
    }

    @DeleteMapping("/roles/{roleId}/permissions/{permissionId}")
    @ResponseStatus(NO_CONTENT)
    public void removePermission(@PathVariable Long roleId, @PathVariable Long permissionId) {
        removePermission.removePermission(mapper.toDomain(permissionId, roleId));
    }
}
