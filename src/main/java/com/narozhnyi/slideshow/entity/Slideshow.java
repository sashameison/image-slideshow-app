package com.narozhnyi.slideshow.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "slideshows")
@EntityListeners(AuditingEntityListener.class)
public class Slideshow extends AuditingEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "slideshowSeqGen")
  @SequenceGenerator(name = "slideshowSeqGen", sequenceName = "slideshow_id_seq", allocationSize = 1)
  private Long id;
  private String title;
}
