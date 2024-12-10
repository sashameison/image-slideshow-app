package com.narozhnyi.slideshow.controller;

import static org.springframework.http.HttpStatus.NO_CONTENT;

import com.narozhnyi.slideshow.dto.ImageCreateDto;
import com.narozhnyi.slideshow.dto.ImageDto;
import com.narozhnyi.slideshow.service.ImageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("api/v1/images")
public class ImageController {

  private final ImageService imageService;

  @GetMapping("/search")
  public Page<ImageDto> findImages(@RequestParam String url, @PageableDefault Pageable pageable) {
    return imageService.findByUrlContaining(url, pageable);
  }
  @PostMapping
  public ImageDto create(@Valid @RequestBody ImageCreateDto imageCreateDto) {
    return imageService.save(imageCreateDto);
  }

  @ResponseStatus(NO_CONTENT)
  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable("id") Long id) {
    imageService.deleteById(id);
  }

}
