package edu.raijin.identity.domain.usecase;

import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.identity.domain.model.ComplementUser;

@UseCase
public interface ConfirmUserUseCase {

    ComplementUser confirm(String code, String email);
}
