package com.pellto.youtoy.domain.channel.domain;

import com.pellto.youtoy.global.util.General;
import com.pellto.youtoy.global.util.RandomString;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class ChannelInfo {

  @Column(name = "handle", nullable = false)
  private String handle;
  @Column(name = "display_name", nullable = false)
  private String displayName;
  @Column(name = "description", nullable = false)
  private String description;
  @Column(name = "banner_path", nullable = false)
  private String bannerPath;
  @Column(name = "profile_path", nullable = false)
  private String profilePath;

  public ChannelInfo(
      String handle,
      String displayName,
      String description,
      String bannerPath,
      String profilePath
  ) {
    this.handle = General.setNullInput(handle, generateRandomHandle());
    this.displayName = General.setNullInput(displayName, this.handle);
    this.description = General.setNullInput(description, "");
    this.bannerPath = General.setNullInput(bannerPath, "");
    this.profilePath = General.setNullInput(profilePath, "");
  }

  private String generateRandomHandle() {
    String prefix = "@user-";
    return prefix + RandomString.make();
  }
}
