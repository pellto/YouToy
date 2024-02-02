package com.pellto.youtoy.video.domain.port.in;

import com.pellto.youtoy.global.dto.video.VideoDto;

public interface GetVideoInfosUsecase {

  VideoDto getVideoInfo(Long id);
}
