package edu.raijin.identity.domain.usecase;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.identity.domain.model.ComplementUser;

@UseCase
public interface LoginUserUseCase {

    ComplementUser login(String email, String password);
}
