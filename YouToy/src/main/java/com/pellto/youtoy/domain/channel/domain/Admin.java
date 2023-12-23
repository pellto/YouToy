package com.pellto.youtoy.domain.channel.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import com.pellto.youtoy.global.util.General;
import com.pellto.youtoy.global.util.Temporal;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@Table(name = "admin")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Admin {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "admin_id")
  private final Long id;
  @Column(name = "created_at", nullable = false)
  private final LocalDateTime createdAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  @JoinColumn(referencedColumnName = "channel_id", name = "channel_id")
  private final Channel targetChannel;
  @Embedded
  @AttributeOverrides({
      @AttributeOverride(
          name = "value",
          column = @Column(
              name = "admin_uuid", nullable = false
          )
      )
  })
  private final UserUUID adminUuid;

  @Column(name = "auth_level")
  @Enumerated(EnumType.STRING)
  private AuthLevel authLevel;

  @Builder
  public Admin(Long id, AuthLevel authLevel, LocalDateTime createdAt, Channel targetChannel,
      UserUUID adminUuid) {
    this.id = id;
    this.createdAt = Temporal.createdAt(createdAt);
    this.targetChannel = Objects.requireNonNull(targetChannel);
    this.adminUuid = Objects.requireNonNull(adminUuid);
    this.authLevel = General.setNullInput(authLevel, AuthLevel.VIEWER);
  }

  public void changeAuthLevel(AuthLevel authLevel) {
    this.authLevel = authLevel;
  }
}
