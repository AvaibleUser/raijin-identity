package edu.raijin.identity.domain.usecase;

import java.util.UUID;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.identity.domain.model.User;

@UseCase
public interface RegisterUserUseCase {

    UUID register(User user);
}
