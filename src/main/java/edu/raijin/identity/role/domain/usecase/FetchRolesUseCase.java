package edu.raijin.identity.role.domain.usecase;

import org.springframework.data.domain.Pageable;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.UseCase;
import edu.raijin.identity.role.domain.model.Role;

@UseCase
public interface FetchRolesUseCase {

    Paged<Role> fetchAll(Pageable pageable);
}
