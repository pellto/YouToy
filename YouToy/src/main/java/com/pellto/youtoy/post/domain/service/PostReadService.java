package com.pellto.youtoy.post.domain.service;

import com.pellto.youtoy.post.domain.port.in.ReadPostUsecase;
import com.pellto.youtoy.post.domain.port.out.LoadPostPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostReadService implements ReadPostUsecase {

  private final LoadPostPort loadPostPort;

  @Override
  public boolean isExistPost(Long postId) {
    return loadPostPort.isExistById(postId);
  }
}
