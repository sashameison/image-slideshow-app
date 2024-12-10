package com.narozhnyi.slideshow.service;

import static com.narozhnyi.slideshow.dto.kafka.Event.EventType.CREATE;
import static com.narozhnyi.slideshow.dto.kafka.Event.EventType.DELETE;

import com.narozhnyi.slideshow.config.KafkaTopicProperties;
import com.narozhnyi.slideshow.dto.kafka.Event;
import com.narozhnyi.slideshow.dto.ImageCreateDto;
import com.narozhnyi.slideshow.dto.ImageDto;
import com.narozhnyi.slideshow.mapper.ImageMapper;
import com.narozhnyi.slideshow.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService {

  private final ImageRepository imageRepository;
  private final ImageMapper pictureMapper;
  private final KafkaProducer kafkaProducer;
  private final KafkaTopicProperties kafkaTopicProperties;

  public Page<ImageDto> findByUrlContaining(String url, Pageable pageable) {
    var imagesEntities = imageRepository.findAllByUrlContaining(url, pageable);
    return imagesEntities.map(pictureMapper::toImageDto);
  }

  public ImageDto save(ImageCreateDto imageCreateDto) {
    var pictureEntity = pictureMapper.toEntity(imageCreateDto);
    var result = pictureMapper.toImageDto(imageRepository.save(pictureEntity));
    kafkaProducer.sendEvent(kafkaTopicProperties.getCreateTopicName(), new Event(result.getId(), CREATE));
    return result;
  }

  public void deleteById(Long id) {
    imageRepository.deleteById(id);
    kafkaProducer.sendEvent(kafkaTopicProperties.getDeleteTopicName(), new Event(id, DELETE));
  }
}
