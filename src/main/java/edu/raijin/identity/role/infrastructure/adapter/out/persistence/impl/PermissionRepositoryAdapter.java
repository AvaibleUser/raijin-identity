package edu.raijin.identity.role.infrastructure.adapter.out.persistence.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;

import edu.raijin.identity.role.domain.model.Permission;
import edu.raijin.identity.role.domain.model.RolePermission;
import edu.raijin.identity.role.domain.port.persistence.AddRolePermissionPort;
import edu.raijin.identity.role.domain.port.persistence.FindPermissionsPort;
import edu.raijin.identity.role.domain.port.persistence.RemoveRolePermissionPort;
import edu.raijin.identity.role.infrastructure.adapter.out.persistence.entity.PermissionsEntity;
import edu.raijin.identity.role.infrastructure.adapter.out.persistence.entity.RolesEntity;
import edu.raijin.identity.role.infrastructure.adapter.out.persistence.mapper.PermissionEntityMapper;
import edu.raijin.identity.role.infrastructure.adapter.out.persistence.repository.JpaPermissionRepository;
import edu.raijin.identity.role.infrastructure.adapter.out.persistence.repository.JpaRoleRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PermissionRepositoryAdapter
        implements FindPermissionsPort, AddRolePermissionPort, RemoveRolePermissionPort {

    private final JpaPermissionRepository permissionRepository;
    private final JpaRoleRepository roleRepository;
    private final PermissionEntityMapper mapper;

    @Override
    public boolean hasPermission(Long roleId, Long permissionId) {
        return roleRepository.existsByIdAndPermissionsIdAndActiveTrue(roleId, permissionId);
    }

    @Override
    public void addPermission(RolePermission rolePermission) {
        RolesEntity role = roleRepository.findById(rolePermission.getRoleId()).get();
        PermissionsEntity permission = permissionRepository.findById(rolePermission.getPermissionId()).get();

        Hibernate.initialize(role.getPermissions());
        role.getPermissions().add(permission);

        roleRepository.save(role);
    }

    @Override
    public void removePermission(RolePermission rolePermission) {
        RolesEntity role = roleRepository.findById(rolePermission.getRoleId()).get();
        PermissionsEntity permission = permissionRepository.findById(rolePermission.getPermissionId()).get();

        Hibernate.initialize(role.getPermissions());
        role.getPermissions().remove(permission);

        roleRepository.save(role);
    }

    @Override
    public boolean existsRole(Long roleId) {
        return roleRepository.existsByIdAndActiveTrue(roleId);
    }

    @Override
    public List<Permission> findAll() {
        List<PermissionsEntity> permissions = permissionRepository.findAllByActiveTrue();
        return mapper.toDomain(permissions);
    }

    @Override
    public List<Permission> findAllByRoleId(Long roleId) {
        List<PermissionsEntity> permissions = permissionRepository.findAllByRolesIdAndActiveTrue(roleId);
        return mapper.toDomain(permissions);
    }
}
