package com.narozhnyi.slideshow.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "proof_of_play")
@EntityListeners(AuditingEntityListener.class)
public class ProofOfPlay extends AuditingEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "proofOfPlaySeqGen")
  @SequenceGenerator(name = "proofOfPlaySeqGen", sequenceName = "proof_of_play_id_seq", allocationSize = 1)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  private Image image;

  @ManyToOne(fetch = FetchType.LAZY)
  private Slideshow slideshow;
}
