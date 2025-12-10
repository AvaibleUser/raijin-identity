package edu.raijin.identity.domain.port.utility;

import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;

@Port
public interface TokenGeneratorPort {

    String generateToken(UUID userId, Object role);
}
