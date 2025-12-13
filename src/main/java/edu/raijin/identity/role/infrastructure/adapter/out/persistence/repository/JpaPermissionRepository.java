package edu.raijin.identity.role.infrastructure.adapter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.raijin.identity.role.infrastructure.adapter.out.persistence.entity.PermissionsEntity;

@Repository
public interface JpaPermissionRepository extends JpaRepository<PermissionsEntity, Long> {

}
