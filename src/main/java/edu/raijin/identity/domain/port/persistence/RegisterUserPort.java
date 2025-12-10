package edu.raijin.identity.domain.port.persistence;

import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.identity.domain.model.User;

@Port
public interface RegisterUserPort {

    boolean exists(String email);

    UUID register(User user);
}
