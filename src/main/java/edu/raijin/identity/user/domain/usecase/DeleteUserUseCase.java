package edu.raijin.identity.user.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;

@UseCase
public interface DeleteUserUseCase {

    void delete(UUID id);
}
