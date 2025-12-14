package edu.raijin.identity.role.application.service;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.identity.role.domain.model.Role;
import edu.raijin.identity.role.domain.port.persistence.FindRolePort;
import edu.raijin.identity.role.domain.usecase.FetchRolesUseCase;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FetchRolesService implements FetchRolesUseCase {

    private final FindRolePort fetchRole;

    @Override
    public Paged<Role> fetchAll(Pageable pageable) {
        return fetchRole.findAll(pageable);
    }
}
