package com.pellto.youtoy.video.domain.model;

import com.pellto.youtoy.global.dto.video.VideoDetailInfoDto;
import com.pellto.youtoy.global.util.General;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;

@Getter
public class VideoDetailInfo {

  private final String title;
  private final String description;
  private final String hashTags;

  @Builder
  public VideoDetailInfo(String title, String description, String hashTags) {
    this.title = Objects.requireNonNull(title);
    this.description = General.setNullInput(description, "DEFAULT_DESCRIPTION");
    this.hashTags = hashTags;
  }

  public VideoDetailInfoDto toDto() {
    return VideoDetailInfoDto.builder()
        .title(title)
        .description(description)
        .hashTags(hashTags)
        .build();
  }
}
