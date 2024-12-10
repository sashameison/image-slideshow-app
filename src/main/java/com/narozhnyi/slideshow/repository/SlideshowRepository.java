package com.narozhnyi.slideshow.repository;

import com.narozhnyi.slideshow.entity.Slideshow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlideshowRepository extends JpaRepository<Slideshow, Long> {
}
