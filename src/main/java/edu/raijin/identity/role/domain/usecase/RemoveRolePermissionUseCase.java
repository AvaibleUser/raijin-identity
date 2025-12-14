package edu.raijin.identity.role.domain.usecase;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.identity.role.domain.model.RolePermission;

@UseCase
public interface RemoveRolePermissionUseCase {

    void removePermission(RolePermission permission);
}
