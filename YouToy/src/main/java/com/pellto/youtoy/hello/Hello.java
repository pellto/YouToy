package com.pellto.youtoy.hello;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Hello {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String content;

  @Builder
  public Hello(String content) {
    this.content = content;
  }
}
