package com.narozhnyi.slideshow.mapper;

import com.narozhnyi.slideshow.dto.ProofOfPlayDto;
import com.narozhnyi.slideshow.entity.ProofOfPlay;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProofOfPlayMapper {

  ProofOfPlayDto toProofOfPlayDto(ProofOfPlay proofOfPlay);

}
