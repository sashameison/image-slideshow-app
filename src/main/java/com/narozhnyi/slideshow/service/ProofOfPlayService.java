package com.narozhnyi.slideshow.service;

import com.narozhnyi.slideshow.dto.ProofOfPlayCreateDto;
import com.narozhnyi.slideshow.dto.ProofOfPlayDto;
import com.narozhnyi.slideshow.entity.ProofOfPlay;
import com.narozhnyi.slideshow.mapper.ProofOfPlayMapper;
import com.narozhnyi.slideshow.repository.ImageRepository;
import com.narozhnyi.slideshow.repository.ProofOfPlayRepository;
import com.narozhnyi.slideshow.repository.SlideshowRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ProofOfPlayService {

  private final ProofOfPlayRepository proofOfPlayRepository;
  private final ProofOfPlayMapper proofOfPlayMapper;
  private final ImageRepository imageRepository;
  private final SlideshowRepository slideshowRepository;

  @Transactional
  public ProofOfPlayDto track(ProofOfPlayCreateDto proofOfPlayCreateDto) {
    var imageEntity = imageRepository.findById(proofOfPlayCreateDto.getImageId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));

    var slideshowEntity = slideshowRepository.findById(proofOfPlayCreateDto.getSlideshowId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));

    var proofOfPlay = new ProofOfPlay();
    proofOfPlay.setImage(imageEntity);
    proofOfPlay.setSlideshow(slideshowEntity);

    return proofOfPlayMapper.toProofOfPlayDto(proofOfPlayRepository.save(proofOfPlay));
  }

}
