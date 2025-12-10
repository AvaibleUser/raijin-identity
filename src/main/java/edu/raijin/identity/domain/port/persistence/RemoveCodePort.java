package edu.raijin.identity.domain.port.persistence;

import edu.raijin.commons.util.annotation.Port;

@Port
public interface RemoveCodePort {

    boolean remove(String code, String email);
}
