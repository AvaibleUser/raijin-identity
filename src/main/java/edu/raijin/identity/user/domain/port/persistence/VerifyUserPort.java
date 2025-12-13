package edu.raijin.identity.user.domain.port.persistence;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.identity.user.domain.model.User;

@Port
public interface VerifyUserPort {

    User verify(String email);
}
