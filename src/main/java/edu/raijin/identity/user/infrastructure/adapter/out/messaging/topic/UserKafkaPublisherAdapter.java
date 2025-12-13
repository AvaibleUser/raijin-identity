package edu.raijin.identity.user.infrastructure.adapter.out.messaging.topic;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import edu.raijin.commons.infrastructure.adapter.messaging.event.identity.UserEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.identity.role.domain.model.Role;
import edu.raijin.identity.user.domain.model.User;
import edu.raijin.identity.user.domain.port.messaging.RegisteredUserPublisherPort;
import edu.raijin.identity.user.infrastructure.adapter.config.property.KafkaTopicsProperty;
import edu.raijin.identity.user.infrastructure.adapter.out.messaging.mapper.UserEventMapper;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class UserKafkaPublisherAdapter implements RegisteredUserPublisherPort {

    private final UserEventMapper mapper;
    private final KafkaTopicsProperty kafkaTopics;
    private final KafkaTemplate<String, UserEvent> kafkaTemplate;

    @Override
    public void publishRegisteredUser(User user, Role role, String code) {
        UserEvent event = mapper.toEvent(user, role.getName(), role.getColor(), code);
        kafkaTemplate.send(kafkaTopics.userCommandsTopic(), "create", event);
    }
}
