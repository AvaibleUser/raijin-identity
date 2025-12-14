package edu.raijin.identity.user.domain.usecase;

import org.springframework.data.domain.Pageable;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.identity.user.domain.model.User;

@UseCase
public interface FetchUsersUseCase {

    Paged<User> fetchAll(Pageable pageable);
}
