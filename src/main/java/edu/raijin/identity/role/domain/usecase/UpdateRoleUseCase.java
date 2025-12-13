package edu.raijin.identity.role.domain.usecase;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.identity.role.domain.model.Role;

@UseCase
public interface UpdateRoleUseCase {

    Role update(Long roleId, Role role);
}
