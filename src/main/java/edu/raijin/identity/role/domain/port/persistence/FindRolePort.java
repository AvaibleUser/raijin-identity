package edu.raijin.identity.role.domain.port.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Port;
import edu.raijin.identity.role.domain.model.Role;

@Port
public interface FindRolePort {

    Optional<Role> findById(Long id);

    Role findRoleByName(String name);

    Role findRoleByUserId(UUID userId);

    Paged<Role> findAll(Pageable pageable);
}
