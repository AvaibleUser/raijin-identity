package edu.raijin.identity.role.domain.usecase;

import java.util.List;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.identity.role.domain.model.Permission;

@UseCase
public interface FetchRolePermissionsUseCase {

    List<Permission> findAllRolePermissions(Long roleId);
}
