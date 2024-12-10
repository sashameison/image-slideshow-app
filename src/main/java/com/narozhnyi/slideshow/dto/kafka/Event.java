package com.narozhnyi.slideshow.dto.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {

  private Long imageId;
  private EventType eventType;

  public enum EventType {
    CREATE,
    DELETE
  }
}
