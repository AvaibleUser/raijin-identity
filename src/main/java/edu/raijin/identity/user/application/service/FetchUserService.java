package edu.raijin.identity.user.application.service;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.identity.user.domain.model.User;
import edu.raijin.identity.user.domain.port.persistence.FindUserPort;
import edu.raijin.identity.user.domain.port.persistence.FindUsersPort;
import edu.raijin.identity.user.domain.usecase.FetchUserUseCase;
import edu.raijin.identity.user.domain.usecase.FetchUsersUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FetchUserService implements FetchUserUseCase, FetchUsersUseCase {

    private final FindUsersPort findAll;
    private final FindUserPort findUser;

    @Override
    public Paged<User> fetchAll(Pageable pageable) {
        return findAll.findAll(pageable);
    }

    @Override
    public User fetchById(UUID id) {
        return findUser.findById(id)
                .orElseThrow(() -> new ValueNotFoundException("El usuario no se encuentra registrado"));
    }
}
