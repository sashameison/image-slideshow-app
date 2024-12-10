package com.narozhnyi.slideshow.mapper;

import com.narozhnyi.slideshow.dto.SlideshowCreateDto;
import com.narozhnyi.slideshow.entity.Slideshow;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SlideshowMapper {

  Slideshow toSlideshow(SlideshowCreateDto slideshowCreateDto);


}
