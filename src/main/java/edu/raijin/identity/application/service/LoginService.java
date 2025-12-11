package edu.raijin.identity.application.service;

import org.springframework.stereotype.Service;

import edu.raijin.commons.util.exception.BadRequestException;
import edu.raijin.identity.domain.model.ComplementUser;
import edu.raijin.identity.domain.model.Role;
import edu.raijin.identity.domain.model.User;
import edu.raijin.identity.domain.port.persistence.FindRolePort;
import edu.raijin.identity.domain.port.persistence.FindUserPort;
import edu.raijin.identity.domain.port.utility.EncryptPort;
import edu.raijin.identity.domain.port.utility.TokenGeneratorPort;
import edu.raijin.identity.domain.usecase.LoginUserUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService implements LoginUserUseCase {

    private final FindUserPort findUser;
    private final FindRolePort findRole;
    private final EncryptPort encrypt;
    private final TokenGeneratorPort tokenGenerator;

    @Override
    public ComplementUser login(String email, String password) {
        User user = null;
        try {
            user = findUser.findByEmail(email)
                    .filter(dbUser -> encrypt.matches(password, dbUser.getPassword()))
                    .filter(User::checkAuthenticated)
                    .orElseThrow(() -> new BadRequestException("El email o la contraseña es incorrecta"));
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(e.getMessage());
        }

        Role role = findRole.findRoleByUserId(user.getId());
        String token = tokenGenerator.generateToken(user.getId(), role.getName());

        return ComplementUser.builder()
                .user(user)
                .role(role)
                .token(token)
                .build();
    }
}
