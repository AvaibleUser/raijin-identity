package edu.raijin.identity.user.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.identity.role.domain.model.Role;
import edu.raijin.identity.role.domain.port.persistence.FindRolePort;
import edu.raijin.identity.user.domain.model.ComplementUser;
import edu.raijin.identity.user.domain.model.User;
import edu.raijin.identity.user.domain.port.persistence.RemoveCodePort;
import edu.raijin.identity.user.domain.port.persistence.VerifyUserPort;
import edu.raijin.identity.user.domain.port.utility.TokenGeneratorPort;
import edu.raijin.identity.user.domain.usecase.ConfirmUserUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConfirmService implements ConfirmUserUseCase {

    private final VerifyUserPort verifyUser;
    private final FindRolePort findRole;
    private final RemoveCodePort removeCode;
    private final TokenGeneratorPort tokenGenerator;

    @Override
    public ComplementUser confirm(String code, String email) {
        if (!removeCode.remove(code, email)) {
            throw new ValueNotFoundException("El código no se encuentra registrado o ha expirado");
        }

        User user = verifyUser.verify(email);
        Role role = findRole.findRoleByUserId(user.getId());
        String token = tokenGenerator.generateToken(user.getId(), role.getName(), List.of());

        return ComplementUser.builder()
                .user(user)
                .role(role)
                .token(token)
                .build();
    }
}
