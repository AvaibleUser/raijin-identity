package edu.raijin.identity.user.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.identity.role.domain.model.Role;
import edu.raijin.identity.role.domain.port.persistence.FindRolePort;
import edu.raijin.identity.user.domain.model.User;
import edu.raijin.identity.user.domain.port.messaging.UpdatedUserPublisherPort;
import edu.raijin.identity.user.domain.port.persistence.UpdateUserPort;
import edu.raijin.identity.user.domain.port.utility.EncryptPort;
import edu.raijin.identity.user.domain.usecase.UpdateUserUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateUserService implements UpdateUserUseCase {

    private final UpdateUserPort update;
    private final EncryptPort encrypt;
    private final FindRolePort findRole;
    private final UpdatedUserPublisherPort publisher;

    @Override
    @Transactional
    public User update(UUID id, User user) {
        User updated = update.findById(id)
                .orElseThrow(() -> new ValueNotFoundException("El usuario no se encuentra registrado"));

        if (user.getPassword() != null) {
            String password = encrypt.encrypt(user.getPassword());
            user.setPassword(password);
        }
        updated.update(user);
        updated.checkValidRegistration();

        User result = update.update(updated);
        Role role = findRole.findById(result.getRoleId()).get();

        publisher.publishUpdatedUser(result, role);
        return result;
    }
}
