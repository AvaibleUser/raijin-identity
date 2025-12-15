package edu.raijin.identity.user.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.identity.role.domain.port.persistence.FindRolePort;
import edu.raijin.identity.user.domain.model.User;
import edu.raijin.identity.user.domain.port.persistence.UpdateUserPort;
import edu.raijin.identity.user.domain.usecase.ChangeUserRoleUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChangeUserRoleService implements ChangeUserRoleUseCase {

    private final UpdateUserPort update;
    private final FindRolePort findRole;

    @Override
    public void changeRole(UUID id, Long role) {
        User user = update.findById(id)
                .orElseThrow(() -> new ValueNotFoundException("El usuario no se encuentra registrado"));
        findRole.findById(role)
                .orElseThrow(() -> new ValueNotFoundException("El rol no se encuentra registrado"));

        user.setRoleId(role);
        update.update(user);
    }
}
