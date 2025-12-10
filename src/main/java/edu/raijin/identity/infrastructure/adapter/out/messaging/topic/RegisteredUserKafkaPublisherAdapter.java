package edu.raijin.identity.infrastructure.adapter.out.messaging.topic;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import edu.raijin.commons.infrastructure.adapter.messaging.event.RegisteredUserEvent;
import edu.raijin.commons.util.annotation.Adapter;
import edu.raijin.identity.domain.model.User;
import edu.raijin.identity.domain.port.messaging.RegisteredUserPublisherPort;
import edu.raijin.identity.infrastructure.adapter.config.property.KafkaTopicsProperty;
import edu.raijin.identity.infrastructure.adapter.out.messaging.mapper.UserEventMapper;
import lombok.RequiredArgsConstructor;

@Adapter
@Component
@RequiredArgsConstructor
public class RegisteredUserKafkaPublisherAdapter implements RegisteredUserPublisherPort {

    private final UserEventMapper UserEventMapper;
    private final KafkaTopicsProperty kafkaTopics;
    private final KafkaTemplate<String, RegisteredUserEvent> kafkaTemplate;

    @Override
    public void publish(User user, String code) {
        RegisteredUserEvent event = UserEventMapper.toEvent(user, code);
        kafkaTemplate.send(kafkaTopics.registeredUser(), "user", event);
    }
}
