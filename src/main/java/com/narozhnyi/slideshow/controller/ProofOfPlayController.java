package com.narozhnyi.slideshow.controller;

import com.narozhnyi.slideshow.dto.ProofOfPlayCreateDto;
import com.narozhnyi.slideshow.dto.ProofOfPlayDto;
import com.narozhnyi.slideshow.service.ProofOfPlayService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/proof-of-play")
public class ProofOfPlayController {

  private final ProofOfPlayService proofOfPlayService;

  @GetMapping("/slideshow/{slideshow-id}/proof-of-play/{image-id}")
  public ProofOfPlayDto trackImagePlay(
      @PathVariable("slideshow-id") Long slideshowId,
      @PathVariable("image-id") Long imageId) {

  return proofOfPlayService.track(new ProofOfPlayCreateDto(imageId, slideshowId));
  }
}
