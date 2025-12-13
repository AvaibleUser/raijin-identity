package edu.raijin.identity.user.domain.port.persistence;

import edu.raijin.commons.util.annotation.Port;

@Port
public interface RegisterCodePort {

    void register(String email, String code);
}
