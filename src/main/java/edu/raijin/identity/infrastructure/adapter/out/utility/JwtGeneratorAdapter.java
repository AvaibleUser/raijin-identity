package edu.raijin.identity.infrastructure.adapter.out.utility;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import edu.raijin.commons.infrastructure.config.property.TokenProperty;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.identity.domain.port.utility.TokenGeneratorPort;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class JwtGeneratorAdapter implements TokenGeneratorPort {

    private final TokenProperty tokenProperty;
    private final JwtEncoder jwtEncoder;

    @Override
    public String generateToken(UUID userId, String role, List<String> permissions) {
        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(tokenProperty.expirationTime(), tokenProperty.timeUnit()))
                .subject(userId.toString())
                .claim("role", role)
                .claim("permissions", permissions)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
