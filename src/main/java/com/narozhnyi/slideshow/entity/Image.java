package com.narozhnyi.slideshow.entity;

import java.time.Duration;

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
@Table(name = "images")
@EntityListeners(AuditingEntityListener.class)
public class Image extends AuditingEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "imageSeqGen")
  @SequenceGenerator(name = "imageSeqGen", sequenceName = "image_id_seq", allocationSize = 1)
  private Long id;
  private String url;
  private Duration duration;

  @ManyToOne(fetch = FetchType.LAZY)
  private Slideshow slideshow;
}
