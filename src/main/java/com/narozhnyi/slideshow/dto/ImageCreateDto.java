package com.narozhnyi.slideshow.dto;

import static com.narozhnyi.slideshow.util.Constant.PICTURE_URL_REGEX;

import java.time.Duration;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageCreateDto {

  @Pattern(regexp = PICTURE_URL_REGEX)
  private String url;
  private Duration duration;
}
