package edu.raijin.identity.role.domain.port.persistence;

import java.util.List;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.identity.role.domain.model.Permission;

@Port
public interface FindPermissionsPort {

    boolean existsRole(Long roleId);

    List<Permission> findAll();

    List<Permission> findAllByRoleId(Long roleId);
}
