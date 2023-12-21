package com.pellto.youtoy.domain.channel.domain;

import com.pellto.youtoy.global.util.General;
import com.pellto.youtoy.global.util.RandomString;
import com.pellto.youtoy.global.util.Temporal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "channel")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Channel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "channel_id")
  private final Long id;
  @Column(name = "created_at")
  private final LocalDateTime createdAt;
  @Column(name = "handle")
  private String handle;
  @Column(name = "display_name")
  private String displayName;
  @Column(name = "description")
  private String description;
  @Column(name = "banner")
  private String banner;
  @Column(name = "profile")
  private String profile;
  @Column(name = "modified_at")
  private LocalDateTime modifiedAt;

  @Builder
  public Channel(Long id, LocalDateTime createdAt, String handle, String displayName,
      String description, String banner, String profile, LocalDateTime modifiedAt) {
    this.id = id;
    this.createdAt = Temporal.createdAt(createdAt);
    this.handle = General.setNullInput(handle, generateRandomHandle());
    this.displayName = General.setNullInput(displayName, this.handle);
    this.description = General.setNullInput(description, "");
    this.banner = General.setNullInput(banner, "");
    this.profile = General.setNullInput(profile, "");
    this.modifiedAt = General.setNullInput(modifiedAt, this.createdAt);
  }

  private String generateRandomHandle() {
    String prefix = "@user-";
    int initHandleLength = 10;
    boolean useLetters = true;
    boolean useNumbers = true;

    String handle = RandomString.make(initHandleLength, useLetters, useNumbers);
    return prefix + handle;
  }
}
