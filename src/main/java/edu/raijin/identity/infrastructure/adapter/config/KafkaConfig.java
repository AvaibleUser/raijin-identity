package edu.raijin.identity.infrastructure.adapter.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import edu.raijin.identity.infrastructure.adapter.config.property.KafkaTopicsProperty;

@Configuration
public class KafkaConfig {

    @Bean
    NewTopic topic(KafkaTopicsProperty kafkaTopics) {
        return TopicBuilder.name(kafkaTopics.userCommandsTopic())
                .partitions(5)
                .replicas(1)
                .build();
    }
}
