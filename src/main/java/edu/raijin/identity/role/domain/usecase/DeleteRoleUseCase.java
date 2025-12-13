package edu.raijin.identity.role.domain.usecase;

import edu.raijin.commons.util.annotation.UseCase;

@UseCase
public interface DeleteRoleUseCase {

    void delete(Long id);
}
