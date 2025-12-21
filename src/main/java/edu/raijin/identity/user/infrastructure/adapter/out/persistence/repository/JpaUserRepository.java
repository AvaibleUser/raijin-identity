package edu.raijin.identity.user.infrastructure.adapter.out.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import edu.raijin.identity.user.infrastructure.adapter.out.persistence.entity.UsersEntity;

@Repository
public interface JpaUserRepository extends JpaRepository<UsersEntity, UUID>, JpaSpecificationExecutor<UsersEntity> {

    boolean existsByEmail(String email);

    Optional<UsersEntity> findByEmail(String email);

    Optional<UsersEntity> findByIdAndActiveTrue(UUID id);

    Page<UsersEntity> findAllByActiveTrue(Pageable pageable);
}
