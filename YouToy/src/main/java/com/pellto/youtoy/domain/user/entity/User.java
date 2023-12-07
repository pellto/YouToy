package com.pellto.youtoy.domain.user.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {

  private final Long id;
  private final LocalDateTime createdAt;
  private String email;
  private String pwd;
  private String name;
  private LocalDate birthDate;

  @Builder
  public User(
      Long id,
      String email,
      String pwd,
      String name,
      LocalDate birthDate,
      LocalDateTime createdAt
  ) {
    this.id = id;
    this.email = Objects.requireNonNull(email);
    this.pwd = Objects.requireNonNull(pwd);
    this.name = name == null ? "user" : name;
    this.birthDate = Objects.requireNonNull(birthDate);
    this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
  }
}
