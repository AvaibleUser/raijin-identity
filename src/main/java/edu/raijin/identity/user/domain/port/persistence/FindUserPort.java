package edu.raijin.identity.user.domain.port.persistence;

import java.util.Optional;
import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.identity.user.domain.model.User;

@Port
public interface FindUserPort {

    Optional<User> findById(UUID id);

    Optional<User> findByEmail(String email);
}
