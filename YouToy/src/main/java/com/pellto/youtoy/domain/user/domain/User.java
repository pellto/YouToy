package com.pellto.youtoy.domain.user.domain;

import com.pellto.youtoy.global.util.Temporal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user_id")
  private final Long id;
  @Column(name = "email")
  private final String email;
  @Column(name = "created_at")
  private final LocalDateTime createdAt;
  @Column(name = "birth_date")
  private final LocalDateTime birthDate;
  @Column(name = "pwd")
  private String pwd;
  @Column(name = "name")
  private String name;
  @Column(name = "premium_level")
  @Enumerated(EnumType.STRING)
  private PremiumLevel premiumLevel;

  @Builder
  public User(Long id, String email, LocalDateTime createdAt, LocalDateTime birthDate, String pwd,
      String name, PremiumLevel premiumLevel) {
    this.id = id;
    this.email = Objects.requireNonNull(email);
    this.createdAt = Temporal.createdAt(createdAt);
    this.birthDate = Temporal.createdAt(birthDate);
    this.pwd = Objects.requireNonNull(pwd);
    this.name = Objects.requireNonNull(name);
    this.premiumLevel = premiumLevel == null ? PremiumLevel.NORMAL : premiumLevel;
  }

  public void changePwd(String pwd, String repeatPwd) {
    if (pwd.equals(repeatPwd)) {
      this.pwd = pwd;
    }
    throw new UnsupportedOperationException("비밀번호 틀림");
  }
}
