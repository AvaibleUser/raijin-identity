package edu.raijin.identity.user.domain.port.utility;

import java.util.List;
import java.util.UUID;

import edu.raijin.commons.util.annotation.Port;

@Port
public interface TokenGeneratorPort {

    String generateToken(UUID userId, String role, List<String> permissions);
}
