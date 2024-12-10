package com.narozhnyi.slideshow.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import com.narozhnyi.slideshow.dto.ImageDto;
import com.narozhnyi.slideshow.dto.SlideshowCreateDto;
import com.narozhnyi.slideshow.entity.Image;
import com.narozhnyi.slideshow.entity.Slideshow;
import com.narozhnyi.slideshow.exception.SlideshowNotFoundException;
import com.narozhnyi.slideshow.mapper.ImageMapper;
import com.narozhnyi.slideshow.mapper.SlideshowMapper;
import com.narozhnyi.slideshow.repository.ImageRepository;
import com.narozhnyi.slideshow.repository.SlideshowRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SlideshowServiceTest {


  @Mock
  private SlideshowRepository slideshowRepository;

  @Mock
  private ImageRepository imageRepository;

  @Mock
  private ImageMapper imageMapper;

  @Mock
  private SlideshowMapper slideshowMapper;

  @InjectMocks
  private SlideshowService slideshowService;

  @Test
  void findByIdOrderByDuration_ShouldReturnSlideshowDto() {
    // given
    Long slideshowId = 1L;
    var slideshowEntity = new Slideshow(slideshowId, "Slideshow 1");
    var imageEntity = new Image(1L, "image1.jpg", Duration.ofSeconds(5), slideshowEntity);
    var imagesEntities = List.of(imageEntity);

    when(imageRepository.findAllBySlideshowIdOrderByDuration(slideshowId)).thenReturn(imagesEntities);
    when(imageMapper.toImageDto(imageEntity)).thenReturn(
        new ImageDto(1L, "image1.jpg", Duration.ofSeconds(5L), Instant.now()));

    // when
    var result = slideshowService.findByIdOrderByDuration(slideshowId);

    // then
    Assertions.assertNotNull(result);
    Assertions.assertEquals(slideshowId, result.getId());
    Assertions.assertEquals("Slideshow 1", result.getTitle());
    Assertions.assertEquals(1, result.getImages().size());
    Assertions.assertEquals("image1.jpg", result.getImages().getFirst().getUrl());
    verify(imageRepository).findAllBySlideshowIdOrderByDuration(slideshowId);
  }

  @Test
  void findByIdOrderByDuration_ShouldThrowSlideshowNotFoundExceptionWhenNoImages() {
    // Arrange
    Long slideshowId = 1L;
    when(imageRepository.findAllBySlideshowIdOrderByDuration(slideshowId)).thenReturn(List.of());

    // Act & Assert
    var exception = assertThrows(SlideshowNotFoundException.class, () ->
        slideshowService.findByIdOrderByDuration(slideshowId)
    );
    Assertions.assertEquals("Slideshow by id: 1, not found", exception.getMessage());
  }

  @Test
  void shouldCreateSlideshowAndReturnDto() {
    // given
    var slideshowCreateDto = new SlideshowCreateDto("New Slideshow", List.of(1L, 2L));
    var slideshowEntity = new Slideshow(1L, "New Slideshow");
    var imageEntity1 = new Image(1L, "image1.jpg", Duration.ofSeconds(5), slideshowEntity);
    var imageEntity2 = new Image(2L, "image2.jpg", Duration.ofSeconds(10), slideshowEntity);
    var imagesEntities = List.of(imageEntity1, imageEntity2);

    when(slideshowMapper.toSlideshow(slideshowCreateDto)).thenReturn(slideshowEntity);
    when(slideshowRepository.save(any())).thenReturn(slideshowEntity);
    when(imageRepository.findAllByIdIn(slideshowCreateDto.getSlideshowImagesIds())).thenReturn(imagesEntities);
    when(imageMapper.toImageDtoList(imagesEntities)).
        thenReturn(List.of(
            new ImageDto(1L, "image1.jpg", Duration.ofSeconds(5L), Instant.now()),
            new ImageDto(2L, "image2.jpg", Duration.ofSeconds(10L), Instant.now()))
        );

    // when
    var result = slideshowService.create(slideshowCreateDto);

    // then
    Assertions.assertNotNull(result);
    Assertions.assertEquals("New Slideshow", result.getTitle());
    Assertions.assertEquals("New Slideshow", result.getTitle());
    Assertions.assertEquals(2, result.getImages().size());

    verify(slideshowRepository).save(slideshowEntity);
    verify(imageRepository).saveAll(imagesEntities);
  }

  @Test
  void shouldDeleteSlideshow() {
    // given
    Long slideshowId = 1L;

    // when
    slideshowService.deleteById(slideshowId);

    // then
    verify(slideshowRepository).deleteById(slideshowId);
  }
}
