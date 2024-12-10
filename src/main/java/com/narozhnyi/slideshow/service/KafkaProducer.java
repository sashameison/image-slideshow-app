package com.narozhnyi.slideshow.service;

import com.narozhnyi.slideshow.dto.kafka.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {

  private final KafkaTemplate<String, Event> kafkaTemplate;

  public void sendEvent(String topic, Event event) {
    kafkaTemplate.send(topic, event);
  }
}
