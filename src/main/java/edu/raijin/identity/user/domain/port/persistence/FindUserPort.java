package edu.raijin.identity.user.domain.port.persistence;

import java.util.Optional;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.identity.user.domain.model.User;

@Port
public interface FindUserPort {

    Optional<User> findByEmail(String email);
}
