package com.pellto.youtoy.video.domain.port.in;

import com.pellto.youtoy.global.dto.video.VideoDto;
import com.pellto.youtoy.global.dto.video.request.UploadVideoRequest;

public interface VideoUploadPort {

  VideoDto upload(UploadVideoRequest request);
}
