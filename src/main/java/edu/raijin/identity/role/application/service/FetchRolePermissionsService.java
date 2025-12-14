package edu.raijin.identity.role.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.identity.role.domain.model.Permission;
import edu.raijin.identity.role.domain.port.persistence.FindPermissionsPort;
import edu.raijin.identity.role.domain.usecase.FetchPermissionsUseCase;
import edu.raijin.identity.role.domain.usecase.FetchRolePermissionsUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FetchRolePermissionsService implements FetchRolePermissionsUseCase, FetchPermissionsUseCase {

    private final FindPermissionsPort findPermissions;

    @Override
    public List<Permission> findAll() {
        return findPermissions.findAll();
    }

    @Override
    public List<Permission> findAllRolePermissions(Long roleId) {
        if (!findPermissions.existsRole(roleId)) {
            throw new ValueNotFoundException("El rol no se encuentra registrado");
        }
        return findPermissions.findAllByRoleId(roleId);
    }
}
