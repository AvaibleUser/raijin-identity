package edu.raijin.identity.domain.port.persistence;

import edu.raijin.commons.util.annotation.Port;

@Port
public interface RegisterCodePort {

    void register(String email, String code);
}
