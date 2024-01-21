package com.pellto.youtoy.channel.domain.model;

import com.pellto.youtoy.global.dto.channel.ChannelInfoDto;
import com.pellto.youtoy.global.util.General;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ChannelInfo {

  private final String displayName;
  private final String description;
  private final String bannerPath;
  private final String profilePath;

  @Builder
  public ChannelInfo(String displayName, String description, String bannerPath,
      String profilePath) {
    this.displayName = Objects.requireNonNull(displayName);
    this.description = General.setNullInput(description, "Not Set description");
    this.bannerPath = General.setNullInput(bannerPath, "DEFAULT PATH");
    this.profilePath = General.setNullInput(profilePath, "DEFAULT PATH");
  }

  public ChannelInfoDto toDto() {
    return new ChannelInfoDto(
        this.displayName,
        this.description,
        this.bannerPath,
        this.profilePath
    );
  }
}
