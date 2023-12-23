package com.pellto.youtoy.domain.user.domain;

import com.pellto.youtoy.global.util.General;
import com.pellto.youtoy.global.util.RandomString;
import com.pellto.youtoy.global.util.Temporal;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
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
  @Column(name = "created_at")
  private final LocalDateTime createdAt;
  @Embedded
  private UserInfo userInfo;
  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "value", column = @Column(name = "uuid"))
  })
  private UserUUID uuid;
  @Column(name = "premium_level")
  @Enumerated(EnumType.STRING)
  private PremiumLevel premiumLevel;

  @Builder
  public User(Long id, UserInfo userInfo, LocalDateTime createdAt, PremiumLevel premiumLevel) {
    this.id = id;
    this.userInfo = Objects.requireNonNull(userInfo);
    this.uuid = generateUserUUID();
    this.createdAt = Temporal.createdAt(createdAt);
    this.premiumLevel = General.setNullInput(premiumLevel, PremiumLevel.NORMAL);
  }

  public void changeUserInfo(UserInfo userInfo) {
    this.userInfo = userInfo;
  }

  @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
  private UserUUID generateUserUUID() {
    int initLength = 20;
    return new UserUUID(RandomString.make(initLength));
  }
}
