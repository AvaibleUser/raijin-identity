package edu.raijin.identity.user.domain.port.utility;

import edu.raijin.commons.util.annotation.Port;

@Port
public interface CodeGeneratorPort {

    String generateCode();
}
