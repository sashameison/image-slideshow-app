package com.narozhnyi.slideshow.listener;

import com.narozhnyi.slideshow.dto.kafka.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventListener {

  @KafkaListener(
      groupId = "slideshow_consumer_group",
      topics = {"image-create", "image-delete"})
  public void listenEvent(Event event) {
    log.info("Receiving event: {}", event);
  }
}
