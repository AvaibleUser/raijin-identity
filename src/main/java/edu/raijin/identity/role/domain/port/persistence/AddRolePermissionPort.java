package edu.raijin.identity.role.domain.port.persistence;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.identity.role.domain.model.RolePermission;

@Port
public interface AddRolePermissionPort {

    boolean hasPermission(Long roleId, Long permissionId);

    void addPermission(RolePermission permission);
}
