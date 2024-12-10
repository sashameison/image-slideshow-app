package com.narozhnyi.slideshow.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Service
@ConfigurationProperties(prefix = "topics")
public class KafkaTopicProperties {

  private String createTopicName;
  private String deleteTopicName;
}
