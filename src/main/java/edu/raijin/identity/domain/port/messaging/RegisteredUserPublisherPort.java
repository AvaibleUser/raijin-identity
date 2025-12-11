package edu.raijin.identity.domain.port.messaging;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.identity.domain.model.User;

@Port
public interface RegisteredUserPublisherPort {

    void publishRegisteredUser(User user, String code);
}
