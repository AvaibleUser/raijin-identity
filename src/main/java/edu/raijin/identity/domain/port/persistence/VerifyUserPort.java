package edu.raijin.identity.domain.port.persistence;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.identity.domain.model.User;

@Port
public interface VerifyUserPort {

    User verify(String email);
}
