package edu.raijin.identity.infrastructure.adapter.out.utility;

import org.springframework.stereotype.Component;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.identity.domain.port.utility.CodeGeneratorPort;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class CodeGeneratorAdapter implements CodeGeneratorPort {

    private final GoogleAuthenticator googleAuth;

    @Override
    public String generateCode() {
        GoogleAuthenticatorKey credentials = googleAuth.createCredentials();
        String code = String.format("%06d", googleAuth.getTotpPassword(credentials.getKey()));
        return code;
    }
}
