package edu.raijin.identity.role.application.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.exception.ValueNotFoundException;
import edu.raijin.identity.role.domain.model.Role;
import edu.raijin.identity.role.domain.port.persistence.FindRolePort;
import edu.raijin.identity.role.domain.usecase.FetchRolesUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FetchRolesService implements FetchRolesUseCase {

    private final FindRolePort fetchRole;

    @Override
    @Transactional
    public Role fetchById(Long id) {
        return fetchRole.findById(id)
                .orElseThrow(() -> new ValueNotFoundException("El rol no se encuentra registrado"));
    }

    @Override
    public Paged<Role> fetchAll(Pageable pageable) {
        return fetchRole.findAll(pageable);
    }
}
