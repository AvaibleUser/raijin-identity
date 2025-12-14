package edu.raijin.identity.user.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.identity.user.domain.model.User;
import edu.raijin.identity.user.domain.port.persistence.UpdateUserPort;
import edu.raijin.identity.user.domain.usecase.BanUserUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BanUserService implements BanUserUseCase {

    private final UpdateUserPort update;

    @Override
    public void ban(UUID id) {
        User user = update.findById(id)
                .orElseThrow(() -> new ValueNotFoundException("El usuario no se encuentra registrado"));

        user.ban();
        update.update(user);
    }
}
