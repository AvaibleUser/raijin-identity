package edu.raijin.identity.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import edu.raijin.commons.util.exception.BadRequestException;
import edu.raijin.commons.util.exception.RequestConflictException;
import edu.raijin.identity.domain.model.User;
import edu.raijin.identity.domain.port.messaging.RegisteredUserPublisherPort;
import edu.raijin.identity.domain.port.persistence.RegisterCodePort;
import edu.raijin.identity.domain.port.persistence.RegisterUserPort;
import edu.raijin.identity.domain.port.utility.CodeGeneratorPort;
import edu.raijin.identity.domain.port.utility.EncryptPort;
import edu.raijin.identity.domain.usecase.RegisterUserUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RegisterService implements RegisterUserUseCase {

    private final RegisterUserPort registerUser;
    private final EncryptPort encrypt;
    private final CodeGeneratorPort codeGenerator;
    private final RegisterCodePort registerCode;
    private final RegisteredUserPublisherPort eventPublisher;

    @Override
    public UUID register(User user) {
        if (registerUser.exists(user.getEmail())) {
            throw new RequestConflictException("El email ya se encuentra registrado");
        }
        try {
            user.checkValidRegistration();
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(e.getMessage());
        }
        String password = encrypt.encrypt(user.getPassword());
        user = user.withPassword(password);
        UUID id = registerUser.register(user);
        user = user.withId(id);

        String code = codeGenerator.generateCode();
        registerCode.register(user.getEmail(), code);
        eventPublisher.publish(user, code);

        return id;
    }
}
