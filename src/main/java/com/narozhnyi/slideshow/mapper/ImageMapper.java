package com.narozhnyi.slideshow.mapper;

import java.util.List;

import com.narozhnyi.slideshow.dto.ImageCreateDto;
import com.narozhnyi.slideshow.dto.ImageDto;
import com.narozhnyi.slideshow.entity.Image;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {

  Image toEntity(ImageCreateDto imageCreateDto);

  ImageDto toImageDto(Image image);

  List<ImageDto> toImageDtoList(List<Image> images);

}
