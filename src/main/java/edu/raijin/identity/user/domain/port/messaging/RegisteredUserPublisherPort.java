package edu.raijin.identity.user.domain.port.messaging;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.identity.role.domain.model.Role;
import edu.raijin.identity.user.domain.model.User;

@Port
public interface RegisteredUserPublisherPort {

    void publishRegisteredUser(User user, Role role, String code);
}
