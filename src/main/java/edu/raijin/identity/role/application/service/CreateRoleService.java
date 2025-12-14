package edu.raijin.identity.role.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.RequestConflictException;
import edu.raijin.identity.role.domain.model.Role;
import edu.raijin.identity.role.domain.port.persistence.RegisterRolePort;
import edu.raijin.identity.role.domain.usecase.CreateRoleUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateRoleService implements CreateRoleUseCase {

    private final RegisterRolePort registerRole;

    @Override
    @Transactional
    public Role create(Role role) {
        if (registerRole.existsByName(role.getName())) {
            throw new RequestConflictException("Ya existe un rol con ese nombre");
        }
        role.checkValidRegistration();
        return registerRole.create(role);
    }
}
