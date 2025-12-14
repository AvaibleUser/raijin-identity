package edu.raijin.identity.role.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.identity.role.domain.model.RolePermission;
import edu.raijin.identity.role.domain.port.persistence.RemoveRolePermissionPort;
import edu.raijin.identity.role.domain.usecase.RemoveRolePermissionUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RemoveRolePermissionService implements RemoveRolePermissionUseCase {

    private final RemoveRolePermissionPort removePermission;

    @Override
    @Transactional
    public void removePermission(RolePermission permission) {
        if (!removePermission.hasPermission(permission.getRoleId(), permission.getPermissionId())) {
            return;
        }
        removePermission.removePermission(permission);
    }
}
