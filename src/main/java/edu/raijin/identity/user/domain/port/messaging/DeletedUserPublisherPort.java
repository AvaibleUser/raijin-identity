package edu.raijin.identity.user.domain.port.messaging;

import edu.raijin.commons.util.annotation.Port;
import edu.raijin.identity.user.domain.model.User;

@Port
public interface DeletedUserPublisherPort {

    void publishDeletedUser(User user);
}