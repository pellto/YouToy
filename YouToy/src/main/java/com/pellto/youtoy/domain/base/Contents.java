package com.pellto.youtoy.domain.base;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Getter
@NoArgsConstructor
public abstract class Contents {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "contents_id")
  private Long id;

  public Contents(Long id) {
    this.id = id;
  }
}
