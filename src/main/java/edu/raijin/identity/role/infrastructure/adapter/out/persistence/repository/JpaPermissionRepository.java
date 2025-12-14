package edu.raijin.identity.role.infrastructure.adapter.out.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.raijin.identity.role.infrastructure.adapter.out.persistence.entity.PermissionsEntity;

@Repository
public interface JpaPermissionRepository extends JpaRepository<PermissionsEntity, Long> {

    List<PermissionsEntity> findAllByActiveTrue();

    List<PermissionsEntity> findAllByRolesIdAndActiveTrue(Long roleId);
}
