package com.narozhnyi.slideshow.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@RequiredArgsConstructor
public class KafkaConfiguration {

  private final KafkaTopicProperties kafkaTopicProperties;

  @Bean
  public NewTopic createTopic() {
    return TopicBuilder.name(kafkaTopicProperties.getCreateTopicName()).build();
  }

  @Bean
  public NewTopic deleteTopic() {
    return TopicBuilder.name(kafkaTopicProperties.getDeleteTopicName()).build();
  }
}
