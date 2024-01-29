package com.pellto.youtoy.post.domain.service;

import com.pellto.youtoy.post.domain.port.in.InterestActionUsecase;
import com.pellto.youtoy.post.domain.port.out.LoadPostPort;
import com.pellto.youtoy.post.domain.port.out.SavePostPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostInterestService implements InterestActionUsecase {

  private final LoadPostPort loadPostPort;
  private final SavePostPort savePostPort;

  @Override
  public void increaseLikeCount(Long postId) {
    var post = loadPostPort.load(postId);
    post.increaseLikeCount();
    savePostPort.save(post);
  }
}
