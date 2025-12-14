package edu.raijin.identity.user.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.identity.user.domain.model.User;

@UseCase
public interface FetchUserUseCase {

    User fetchById(UUID id);
}
