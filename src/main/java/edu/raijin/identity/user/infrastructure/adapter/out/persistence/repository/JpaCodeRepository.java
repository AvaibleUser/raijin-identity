package edu.raijin.identity.user.infrastructure.adapter.out.persistence.repository;

import java.time.Instant;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.raijin.identity.user.infrastructure.adapter.out.persistence.entity.CodesEntity;

@Repository
public interface JpaCodeRepository extends JpaRepository<CodesEntity, String> {

    Optional<CodesEntity> findByCodeAndEmail(String code, String email);

    void deleteByExpirationBefore(Instant expiration);
}
