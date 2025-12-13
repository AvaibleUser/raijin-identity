package edu.raijin.identity.user.infrastructure.adapter.out.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.raijin.identity.user.infrastructure.adapter.out.persistence.entity.UsersEntity;

@Repository
public interface JpaUserRepository extends JpaRepository<UsersEntity, UUID> {

    boolean existsByEmail(String email);

    Optional<UsersEntity> findByEmail(String email);
}
