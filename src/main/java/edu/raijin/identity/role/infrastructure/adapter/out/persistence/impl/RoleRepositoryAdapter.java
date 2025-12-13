package edu.raijin.identity.role.infrastructure.adapter.out.persistence.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.identity.role.domain.model.Role;
import edu.raijin.identity.role.domain.port.persistence.FindRolePort;
import edu.raijin.identity.role.domain.port.persistence.RegisterRolePort;
import edu.raijin.identity.role.domain.port.persistence.UpdateRolePort;
import edu.raijin.identity.role.infrastructure.adapter.out.persistence.entity.PermissionsEntity;
import edu.raijin.identity.role.infrastructure.adapter.out.persistence.entity.RolesEntity;
import edu.raijin.identity.role.infrastructure.adapter.out.persistence.mapper.RoleEntityMapper;
import edu.raijin.identity.role.infrastructure.adapter.out.persistence.repository.JpaPermissionRepository;
import edu.raijin.identity.role.infrastructure.adapter.out.persistence.repository.JpaRoleRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class RoleRepositoryAdapter implements FindRolePort, RegisterRolePort, UpdateRolePort {

    private final JpaRoleRepository roleRepository;
    private final JpaPermissionRepository permissionRepository;
    private final RoleEntityMapper mapper;

    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findByName(name)
                .map(mapper::toDomain)
                .get();
    }

    @Override
    public Role findRoleByUserId(UUID userId) {
        return roleRepository.findByUserId(userId)
                .map(mapper::toDomain)
                .get();
    }

    @Override
    public Paged<Role> fetchAll(Pageable pageable) {
        Page<Role> entities = roleRepository.findAllByActiveTrue(pageable).map(mapper::toDomain);
        return Paged.from(entities);
    }

    @Override
    public boolean exists(String name) {
        return roleRepository.existsByName(name);
    }

    @Override
    public Role create(Role role) {
        RolesEntity entity = mapper.toEntity(role);

        if (role.getPermissions() != null) {
            List<PermissionsEntity> permissions = permissionRepository.findAllById(role.getPermissions());
            entity.setPermissions(permissions);
        }
        return mapper.toDomain(roleRepository.save(entity));
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Role update(Role role) {
        RolesEntity entity = mapper.toEntity(role);

        if (role.getPermissions() != null) {
            List<PermissionsEntity> permissions = permissionRepository.findAllById(role.getPermissions());
            entity.setPermissions(permissions);
        }
        return mapper.toDomain(roleRepository.save(entity));
    }
}
