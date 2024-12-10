package com.narozhnyi.slideshow.repository;

import java.util.List;

import com.narozhnyi.slideshow.entity.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {

  List<Image> findAllByIdIn(List<Long> ids);

  @EntityGraph(attributePaths = "slideshow")
  List<Image> findAllBySlideshowIdOrderByDuration(Long id);

  Page<Image> findAllByUrlContaining(String url, Pageable pageable);
}
