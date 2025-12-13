package edu.raijin.identity.role.domain.port.persistence;

import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.identity.role.domain.model.Role;

@Port
public interface FindRolePort {

    Role findRoleByName(String name);

    Role findRoleByUserId(UUID userId);
}
