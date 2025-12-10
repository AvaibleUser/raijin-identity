package edu.raijin.identity.infrastructure.adapter.out.utility;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.identity.domain.port.utility.EncryptPort;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class EncryptAdapter implements EncryptPort {

    private final PasswordEncoder encoder;

    @Override
    public String encrypt(String str) {
        return encoder.encode(str);
    }

    @Override
    public boolean matches(String str, String encryptedStr) {
        return encoder.matches(str, encryptedStr);
    }
}
