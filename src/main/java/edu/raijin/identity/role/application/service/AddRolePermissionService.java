package edu.raijin.identity.role.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.RequestConflictException;
import edu.raijin.identity.role.domain.model.RolePermission;
import edu.raijin.identity.role.domain.port.persistence.AddRolePermissionPort;
import edu.raijin.identity.role.domain.usecase.AddRolePermissionUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddRolePermissionService implements AddRolePermissionUseCase {

    private final AddRolePermissionPort addPermission;

    @Override
    @Transactional
    public void addPermission(RolePermission permission) {
        if (addPermission.hasPermission(permission.getRoleId(), permission.getPermissionId())) {
            throw new RequestConflictException("El rol ya tiene el permiso");
        }
        permission.checkValidRegistration();
        addPermission.addPermission(permission);
    }
}
