package com.pellto.youtoy.post.domain.port.in;

import com.pellto.youtoy.global.dto.post.PostDto;
import com.pellto.youtoy.global.dto.post.request.WritePostRequest;

public interface WritePostUsecase {

  PostDto write(WritePostRequest request);
}
