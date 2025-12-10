package edu.raijin.identity.domain.port.persistence;

import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.identity.domain.model.Role;

@Port
public interface FindRolePort {

    Role findRoleByUserId(UUID userId);
}
