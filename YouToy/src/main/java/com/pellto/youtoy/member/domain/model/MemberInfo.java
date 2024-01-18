package com.pellto.youtoy.member.domain.model;

import com.pellto.youtoy.global.dto.member.MemberInfoDto;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberInfo {

  private final String email;
  private String pwd;
  private String name;
  private LocalDateTime birthDate;

  @Builder
  public MemberInfo(String email, String pwd, String name, LocalDateTime birthDate) {
    this.email = email;
    this.pwd = pwd;
    this.name = name;
    this.birthDate = birthDate;
    this.valid();
  }

  public void valid() {
    try {
      Objects.requireNonNull(email);
      Objects.requireNonNull(pwd);
      Objects.requireNonNull(name);
      Objects.requireNonNull(birthDate);
    } catch (Exception e) {
      throw new IllegalArgumentException();
    }
  }

  public static MemberInfo fromDto(MemberInfoDto dto) {
    return MemberInfo.builder()
        .email(dto.email())
        .pwd(dto.pwd())
        .name(dto.name())
        .birthDate(dto.birthDate())
        .build();
  }
}
