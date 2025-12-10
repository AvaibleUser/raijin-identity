package edu.raijin.identity.domain.usecase;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.identity.domain.model.User;

@UseCase
public interface ConfirmUserUseCase {

    User confirm(String code, String email);
}
