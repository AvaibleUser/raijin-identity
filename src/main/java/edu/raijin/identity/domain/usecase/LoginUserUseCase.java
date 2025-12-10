package edu.raijin.identity.domain.usecase;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.identity.domain.model.User;

@UseCase
public interface LoginUserUseCase {

    User login(String email, String password);
}
