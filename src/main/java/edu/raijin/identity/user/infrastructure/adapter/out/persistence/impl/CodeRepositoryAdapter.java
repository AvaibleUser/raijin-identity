package edu.raijin.identity.user.infrastructure.adapter.out.persistence.impl;

import java.time.Instant;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.identity.user.domain.port.persistence.RegisterCodePort;
import edu.raijin.identity.user.domain.port.persistence.RemoveCodePort;
import edu.raijin.identity.user.infrastructure.adapter.out.persistence.entity.CodesEntity;
import edu.raijin.identity.user.infrastructure.adapter.out.persistence.repository.JpaCodeRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class CodeRepositoryAdapter implements RegisterCodePort, RemoveCodePort {

    private final JpaCodeRepository codeRepository;

    @Override
    public void register(String email, String code) {
        codeRepository.save(new CodesEntity(code, email));
    }

    @Override
    @Transactional
    public boolean remove(String code, String email) {
        codeRepository.deleteByExpirationBefore(Instant.now());
        return codeRepository.findByCodeAndEmail(code, email)
                .map(codeEntity -> {
                    codeRepository.delete(codeEntity);
                    return true;
                })
                .orElse(false);
    }
}
