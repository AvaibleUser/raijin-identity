package edu.raijin.identity.role.domain.port.persistence;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.identity.role.domain.model.Role;

@Port
public interface RegisterRolePort {

    boolean exists(String name);

    Role create(Role role);
}
