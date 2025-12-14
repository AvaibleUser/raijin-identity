package edu.raijin.identity.user.infrastructure.adapter.out.persistence.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.identity.role.infrastructure.adapter.out.persistence.entity.RolesEntity;
import edu.raijin.identity.role.infrastructure.adapter.out.persistence.repository.JpaRoleRepository;
import edu.raijin.identity.user.domain.model.User;
import edu.raijin.identity.user.domain.port.persistence.FindUserPort;
import edu.raijin.identity.user.domain.port.persistence.FindUsersPort;
import edu.raijin.identity.user.domain.port.persistence.RegisterUserPort;
import edu.raijin.identity.user.domain.port.persistence.UpdateUserPort;
import edu.raijin.identity.user.domain.port.persistence.VerifyUserPort;
import edu.raijin.identity.user.infrastructure.adapter.out.persistence.entity.UsersEntity;
import edu.raijin.identity.user.infrastructure.adapter.out.persistence.mapper.UserEntityMapper;
import edu.raijin.identity.user.infrastructure.adapter.out.persistence.repository.JpaUserRepository;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter
        implements RegisterUserPort, VerifyUserPort, FindUserPort, FindUsersPort, UpdateUserPort {

    private final JpaUserRepository userRepository;
    private final JpaRoleRepository roleRepository;
    private final UserEntityMapper mapper;

    @Override
    public UUID register(User user, Long roleId) {
        RolesEntity role = roleRepository.findById(roleId).get();
        UsersEntity entity = mapper.toEntity(user)
                .toBuilder()
                .role(role)
                .build();

        return userRepository.save(entity).getId();
    }

    @Override
    public boolean exists(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userRepository.findByIdAndActiveTrue(id).map(mapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public User update(User user) {
        UsersEntity entity = mapper.toEntity(user);
        return mapper.toDomain(userRepository.save(entity));
    }

    @Override
    public Paged<User> findAll(Pageable pageable) {
        Page<User> page = userRepository.findAllByActiveTrue(pageable).map(mapper::toDomain);
        return Paged.from(page);
    }
}
