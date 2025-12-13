package edu.raijin.identity.role.infrastructure.adapter.out.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.raijin.identity.role.infrastructure.adapter.out.persistence.entity.RolesEntity;

@Repository
public interface JpaRoleRepository extends JpaRepository<RolesEntity, Long> {

    Optional<RolesEntity> findByName(String name);

    Optional<RolesEntity> findByUserId(UUID userId);
}
