package com.pellto.youtoy.video.domain.port.out;

import com.pellto.youtoy.global.dto.video.VideoDto;

public interface VideoEventPort {

  void requestedVideoUpload(VideoDto dto);
}
