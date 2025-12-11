package edu.raijin.identity.infrastructure.adapter.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("kafka.topics")
public record KafkaTopicsProperty(
        String userCommandsTopic) {
}
