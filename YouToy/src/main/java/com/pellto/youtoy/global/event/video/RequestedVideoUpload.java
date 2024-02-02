package com.pellto.youtoy.global.event.video;

import com.pellto.youtoy.global.dto.video.VideoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class RequestedVideoUpload {

  private VideoDto dto;
  private String publisher;
}
