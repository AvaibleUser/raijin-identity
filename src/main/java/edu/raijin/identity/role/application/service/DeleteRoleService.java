package edu.raijin.identity.role.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.identity.role.domain.model.Role;
import edu.raijin.identity.role.domain.port.persistence.UpdateRolePort;
import edu.raijin.identity.role.domain.usecase.DeleteRoleUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteRoleService implements DeleteRoleUseCase {

    private final UpdateRolePort update;

    @Override
    @Transactional
    public void delete(Long id) {
        Role role = update.findById(id)
                .orElseThrow(() -> new ValueNotFoundException("El rol no se encuentra registrado"));

        role.delete();
        update.update(role);
    }
}
