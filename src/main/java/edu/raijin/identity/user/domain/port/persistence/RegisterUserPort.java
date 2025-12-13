package edu.raijin.identity.user.domain.port.persistence;

import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.identity.user.domain.model.User;

@Port
public interface RegisterUserPort {

    boolean exists(String email);

    UUID register(User user, Long roleId);
}
