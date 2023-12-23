package com.pellto.youtoy.domain.user.domain;

import com.pellto.youtoy.global.util.Temporal;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class UserInfo {

  @Column(name = "email")
  @Email
  private String email;
  @Column(name = "pwd")
  private String pwd;
  @Column(name = "name")
  private String name;
  @Column(name = "birthDate")
  private LocalDateTime birthDate;

  public UserInfo(
      String email,
      String pwd,
      String name,
      LocalDateTime birthDate
  ) {
    this.email = Objects.requireNonNull(email);
    this.pwd = Objects.requireNonNull(pwd);
    this.name = Objects.requireNonNull(name);
    this.birthDate = Temporal.createdAt(birthDate);
  }
}
