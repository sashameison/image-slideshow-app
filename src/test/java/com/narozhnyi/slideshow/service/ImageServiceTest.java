package com.narozhnyi.slideshow.service;

import static com.narozhnyi.slideshow.dto.kafka.Event.EventType.CREATE;
import static com.narozhnyi.slideshow.dto.kafka.Event.EventType.DELETE;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import com.narozhnyi.slideshow.config.KafkaTopicProperties;
import com.narozhnyi.slideshow.dto.ImageCreateDto;
import com.narozhnyi.slideshow.dto.ImageDto;
import com.narozhnyi.slideshow.dto.kafka.Event;
import com.narozhnyi.slideshow.entity.Image;
import com.narozhnyi.slideshow.mapper.ImageMapper;
import com.narozhnyi.slideshow.repository.ImageRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class ImageServiceTest {

  @Mock
  private ImageRepository imageRepository;

  @Mock
  private ImageMapper pictureMapper;

  @Mock
  private KafkaProducer kafkaProducer;

  @Mock
  private KafkaTopicProperties kafkaTopicProperties;

  @InjectMocks
  private ImageService imageService;

  @Test
  void shouldFindByUrlContaining() {
    // given
    var url = "example";
    Pageable pageable = PageRequest.of(0, 10);
    Page<Image> imageEntities = new PageImpl<>(
        List.of(new Image(1L, "example1", Duration.ofSeconds(5), null)));

    when(imageRepository.findAllByUrlContaining(url, pageable)).thenReturn(imageEntities);
    when(pictureMapper.toImageDto(any(Image.class))).thenAnswer(invocation -> {
      Image image = invocation.getArgument(0);
      return new ImageDto(image.getId(), image.getUrl(), image.getDuration(), Instant.now());
    });

    // when
    var result = imageService.findByUrlContaining(url, pageable);

    // then
    assertNotNull(result);
    Assertions.assertEquals(1, result.getContent().size());
    Assertions.assertEquals("example1", result.getContent().getFirst().getUrl());
    verify(imageRepository).findAllByUrlContaining(url, pageable);
  }

  @Test
  void shouldSaveImageAndSendKafkaEvent() {
    //given
    var imageCreateDto = new ImageCreateDto("example2", Duration.ofSeconds(10));
    var imageEntity = new Image(null, "example2", Duration.ofSeconds(10), null);
    var savedEntity = new Image(2L, "example2", Duration.ofSeconds(10), null);
    var savedDto = new ImageDto(2L, "example2", Duration.ofSeconds(10), null);

    when(pictureMapper.toEntity(imageCreateDto)).thenReturn(imageEntity);
    when(imageRepository.save(imageEntity)).thenReturn(savedEntity);
    when(pictureMapper.toImageDto(savedEntity)).thenReturn(savedDto);
    when(kafkaTopicProperties.getCreateTopicName()).thenReturn("create-topic");

    //when
    var result = imageService.save(imageCreateDto);

    // then
    assertNotNull(result);
    Assertions.assertEquals("example2", result.getUrl());
    Assertions.assertEquals(Duration.ofSeconds(10), result.getDuration());
    verify(imageRepository).save(imageEntity);
    verify(kafkaProducer).sendEvent("create-topic", new Event(2L, CREATE));
  }

  @Test
  void shouldDeleteImageAndSendKafkaEvent() {
    // given
    Long imageId = 3L;
    when(kafkaTopicProperties.getDeleteTopicName()).thenReturn("delete-topic");

    //when
    imageService.deleteById(imageId);

    //then
    verify(imageRepository).deleteById(imageId);
    verify(kafkaProducer).sendEvent("delete-topic", new Event(imageId, DELETE));
  }
}

