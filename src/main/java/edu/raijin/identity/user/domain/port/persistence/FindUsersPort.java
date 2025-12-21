package edu.raijin.identity.user.domain.port.persistence;

import org.springframework.data.domain.Pageable;

import edu.raijin.commons.domain.model.Paged;
import edu.raijin.commons.util.annotation.Port;
import edu.raijin.identity.user.domain.model.User;

@Port
public interface FindUsersPort {

    Paged<User> findAll(Pageable pageable, String name);
}
