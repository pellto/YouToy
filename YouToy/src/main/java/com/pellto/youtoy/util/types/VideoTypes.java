package com.pellto.youtoy.util.types;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum VideoTypes {
  COMMENT_TYPE(null, false),
  VIDEO_TYPE(0, true),
  SHORTS_TYPE(1, false),
  ;
  private final Integer value;
  private final boolean isVideo;

  VideoTypes(final Integer value, boolean isVideo) {
    this.value = value;
    this.isVideo = isVideo;
  }
}
