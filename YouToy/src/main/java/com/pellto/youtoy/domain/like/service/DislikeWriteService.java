package com.pellto.youtoy.domain.like.service;

import com.pellto.youtoy.domain.like.dto.CreateLikeCommand;
import com.pellto.youtoy.domain.like.entity.Dislike;
import com.pellto.youtoy.domain.like.repository.DislikeRepository;
import com.pellto.youtoy.util.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class DislikeWriteService {

  private final DislikeRepository dislikeRepository;

  public void cancel(Long id) {
    if (!dislikeRepository.existById(id)) {
      throw new UnsupportedOperationException(ErrorCode.NOT_EXIST_DISLIKE.getMessage());
    }
    dislikeRepository.deleteById(id);
  }

  public Dislike dislike(CreateLikeCommand cmd) {
    if (cmd.videoType() == null && cmd.commentId() == null) {
      throw new UnsupportedOperationException(ErrorCode.UNSUPPORTED_LIKE_CASE.getMessage());
    }

    var dislike = Dislike.builder()
        .videoId(cmd.videoId())
        .videoType(cmd.videoType())
        .commentId(cmd.commentId())
        .userId(cmd.userId())
        .build();

    return dislikeRepository.save(dislike);
  }
}
