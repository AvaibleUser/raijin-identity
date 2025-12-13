package edu.raijin.identity.user.domain.port.utility;

import edu.raijin.commons.util.annotation.Port;

@Port
public interface EncryptPort {

    String encrypt(String str);

    boolean matches(String str, String encryptedStr);
}
