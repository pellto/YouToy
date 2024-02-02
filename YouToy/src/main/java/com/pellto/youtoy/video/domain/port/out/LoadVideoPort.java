package com.pellto.youtoy.video.domain.port.out;

import com.pellto.youtoy.video.domain.model.Video;

public interface LoadVideoPort {

  Video load(Long videoId);
}
