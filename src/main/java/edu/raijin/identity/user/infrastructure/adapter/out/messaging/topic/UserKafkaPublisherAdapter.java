package edu.raijin.identity.user.infrastructure.adapter.out.messaging.topic;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import edu.raijin.commons.infrastructure.adapter.messaging.event.identity.UserEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.identity.role.domain.model.Role;
import edu.raijin.identity.user.domain.model.User;
import edu.raijin.identity.user.domain.port.messaging.DeletedUserPublisherPort;
import edu.raijin.identity.user.domain.port.messaging.RegisteredUserPublisherPort;
import edu.raijin.identity.user.domain.port.messaging.UpdatedUserPublisherPort;
import edu.raijin.identity.user.infrastructure.adapter.config.property.KafkaTopicsProperty;
import edu.raijin.identity.user.infrastructure.adapter.out.messaging.mapper.UserEventMapper;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class UserKafkaPublisherAdapter
        implements RegisteredUserPublisherPort, UpdatedUserPublisherPort, DeletedUserPublisherPort {

    private final UserEventMapper mapper;
    private final KafkaTopicsProperty kafkaTopics;
    private final KafkaTemplate<String, UserEvent> kafkaTemplate;

    @Override
    public void publishRegisteredUser(User user, Role role, String code) {
        UserEvent event = mapper.toEvent(user, role.getName(), role.getColor(), code);
        kafkaTemplate.send(kafkaTopics.userCommandsTopic(), "create", event);
    }

    @Override
    public void publishUpdatedUser(User user, Role role) {
        UserEvent event = mapper.toEvent(user, role.getName(), role.getColor());
        kafkaTemplate.send(kafkaTopics.userCommandsTopic(), "update", event);
    }

    @Override
    public void publishDeletedUser(User user) {
        UserEvent event = mapper.toEvent(user);
        kafkaTemplate.send(kafkaTopics.userCommandsTopic(), "delete", event);
    }
}
