package edu.raijin.identity.user.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.identity.user.domain.model.User;
import edu.raijin.identity.user.domain.port.messaging.DeletedUserPublisherPort;
import edu.raijin.identity.user.domain.port.persistence.UpdateUserPort;
import edu.raijin.identity.user.domain.usecase.DeleteUserUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteUserService implements DeleteUserUseCase {

    private final UpdateUserPort update;
    private final DeletedUserPublisherPort publisher;

    @Override
    public void delete(UUID id) {
        User user = update.findById(id)
                .orElseThrow(() -> new ValueNotFoundException("El usuario no se encuentra registrado"));

        user.delete();
        User result = update.update(user);
        publisher.publishDeletedUser(result);
    }
}
