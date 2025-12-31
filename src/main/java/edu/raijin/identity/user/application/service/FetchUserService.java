package edu.raijin.identity.user.application.service;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.identity.role.domain.model.Role;
import edu.raijin.identity.role.domain.port.persistence.FindRolePort;
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
    private final FindRolePort findRole;
    private final FindUserPort findUser;

    @Override
    @Transactional
    public Paged<User> fetchAll(Pageable pageable, String name) {
        return findAll.findAll(pageable, name);
    }

    @Override
    @Transactional
    public User fetchById(UUID id) {
        return findUser.findById(id)
                .map(user -> {
                    Role role = findRole.findRoleByUserId(user.getId());
                    user.setPermissions(role.getPermissionNames());
                    return user;
                })
                .orElseThrow(() -> new ValueNotFoundException("El usuario no se encuentra registrado"));
    }
}
