package com.narozhnyi.slideshow.controller;

import static org.springframework.http.HttpStatus.NO_CONTENT;

import com.narozhnyi.slideshow.dto.SlideshowCreateDto;
import com.narozhnyi.slideshow.dto.SlideshowDto;
import com.narozhnyi.slideshow.service.SlideshowService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("api/v1/slideshows")
public class SlideshowController {

  private final SlideshowService slideshowService;

  @GetMapping("/{id}/slideshowOrder")
  public SlideshowDto findByIdOrderByDuration(@PathVariable("id") Long id) {
    return slideshowService.findByIdOrderByDuration(id);
  }

  @PostMapping
  public SlideshowDto create(@Valid @RequestBody SlideshowCreateDto slideshowCreateDto) {
    return slideshowService.create(slideshowCreateDto);
  }

  @ResponseStatus(NO_CONTENT)
  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable("id") Long id) {
    slideshowService.deleteById(id);
  }


}
