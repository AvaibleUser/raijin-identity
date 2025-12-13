package edu.raijin.identity.role.domain.port.persistence;

import java.util.Optional;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.identity.role.domain.model.Role;

@Port
public interface UpdateRolePort {

    Optional<Role> findById(Long id);

    Role update(Role role);
}
