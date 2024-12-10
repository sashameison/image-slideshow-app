package com.narozhnyi.slideshow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProofOfPlayCreateDto {

  private Long imageId;
  private Long slideshowId;
}
