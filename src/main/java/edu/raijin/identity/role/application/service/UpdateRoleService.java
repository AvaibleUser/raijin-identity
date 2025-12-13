package edu.raijin.identity.role.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.identity.role.domain.model.Role;
import edu.raijin.identity.role.domain.port.persistence.UpdateRolePort;
import edu.raijin.identity.role.domain.usecase.UpdateRoleUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UpdateRoleService implements UpdateRoleUseCase {

    private final UpdateRolePort update;

    @Override
    @Transactional
    public Role update(Long roleId, Role updated) {
        Role role = update.findById(roleId)
                .orElseThrow(() -> new ValueNotFoundException("El rol no se encuentra registrado"));

        role.update(updated);
        return update.update(role);
    }
}
