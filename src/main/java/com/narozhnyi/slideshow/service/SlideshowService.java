package com.narozhnyi.slideshow.service;

import com.narozhnyi.slideshow.dto.SlideshowCreateDto;
import com.narozhnyi.slideshow.dto.SlideshowDto;
import com.narozhnyi.slideshow.exception.SlideshowNotFoundException;
import com.narozhnyi.slideshow.mapper.ImageMapper;
import com.narozhnyi.slideshow.mapper.SlideshowMapper;
import com.narozhnyi.slideshow.repository.ImageRepository;
import com.narozhnyi.slideshow.repository.SlideshowRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SlideshowService {

  private final SlideshowRepository slideshowRepository;
  private final ImageRepository imageRepository;
  private final ImageMapper imageMapper;
  private final SlideshowMapper slideshowMapper;


  public SlideshowDto findByIdOrderByDuration(Long id) {
    var imagesEntities = imageRepository.findAllBySlideshowIdOrderByDuration(id);

    if (imagesEntities.isEmpty()) {
      throw new SlideshowNotFoundException(String.format("Slideshow by id: %s, not found", id));
    }

    return new SlideshowDto(
        imagesEntities.getFirst().getSlideshow().getId(),
        imagesEntities.getFirst().getSlideshow().getTitle(),
        imagesEntities.stream().map(imageMapper::toImageDto).toList());
  }

  @Transactional
  public SlideshowDto create(SlideshowCreateDto slideshowCreateDto) {
    var imagesEntities = imageRepository.findAllByIdIn(slideshowCreateDto.getSlideshowImagesIds());

    var slideshowEntity = slideshowRepository.save(slideshowMapper.toSlideshow(slideshowCreateDto));

    imagesEntities.forEach(image -> image.setSlideshow(slideshowEntity));
    imageRepository.saveAll(imagesEntities);

    return new SlideshowDto(
        slideshowEntity.getId(),
        slideshowEntity.getTitle(),
        imageMapper.toImageDtoList(imagesEntities));
  }

  public void deleteById(Long id) {
    slideshowRepository.deleteById(id);
  }

}
