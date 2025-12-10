package edu.raijin.identity.domain.port.utility;

import edu.raijin.commons.util.annotation.Port;

@Port
public interface CodeGeneratorPort {

    String generateCode();
}
