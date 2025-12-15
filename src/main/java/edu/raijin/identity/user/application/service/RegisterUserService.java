package edu.raijin.identity.user.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.RequestConflictException;
import edu.raijin.identity.role.domain.model.Role;
import edu.raijin.identity.role.domain.port.persistence.FindRolePort;
import edu.raijin.identity.user.domain.model.User;
import edu.raijin.identity.user.domain.port.messaging.RegisteredUserPublisherPort;
import edu.raijin.identity.user.domain.port.persistence.RegisterCodePort;
import edu.raijin.identity.user.domain.port.persistence.RegisterUserPort;
import edu.raijin.identity.user.domain.port.utility.CodeGeneratorPort;
import edu.raijin.identity.user.domain.port.utility.EncryptPort;
import edu.raijin.identity.user.domain.usecase.RegisterUserUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterUserService implements RegisterUserUseCase {

    private final RegisterUserPort registerUser;
    private final FindRolePort findRole;
    private final EncryptPort encrypt;
    private final CodeGeneratorPort codeGenerator;
    private final RegisterCodePort registerCode;
    private final RegisteredUserPublisherPort eventPublisher;

    @Override
    @Transactional
    public UUID register(User user) {
        if (registerUser.exists(user.getEmail())) {
            throw new RequestConflictException("El email ya se encuentra registrado");
        }
        user.checkValidRegistration();

        Role role = findRole.findRoleByName("USER");

        String password = encrypt.encrypt(user.getPassword());
        user.setPassword(password);
        UUID id = registerUser.register(user, role.getId());
        user.updateId(id);

        String code = codeGenerator.generateCode();
        registerCode.register(user.getEmail(), code);
        eventPublisher.publishRegisteredUser(user, role, code);

        return id;
    }
}
