package edu.raijin.identity.role.infrastructure.adapter.out.persistence.impl;

import java.util.UUID;

import org.springframework.stereotype.Component;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.identity.role.domain.model.Role;
import edu.raijin.identity.role.domain.port.persistence.FindRolePort;
import edu.raijin.identity.role.infrastructure.adapter.out.persistence.mapper.RoleEntityMapper;
import edu.raijin.identity.role.infrastructure.adapter.out.persistence.repository.JpaRoleRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class RoleRepositoryAdapter implements FindRolePort {

    private final JpaRoleRepository jpaRoleRepository;
    private final RoleEntityMapper roleMapper;

    @Override
    public Role findRoleByName(String name) {
        return jpaRoleRepository.findByName(name)
                .map(roleMapper::toDomain)
                .get();
    }

    @Override
    public Role findRoleByUserId(UUID userId) {
        return jpaRoleRepository.findByUserId(userId)
                .map(roleMapper::toDomain)
                .get();
    }
}
