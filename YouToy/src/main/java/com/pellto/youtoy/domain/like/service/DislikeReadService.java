package com.pellto.youtoy.domain.like.service;

import com.pellto.youtoy.domain.like.dto.CreateLikeCommand;
import com.pellto.youtoy.domain.like.dto.LikeDto;
import com.pellto.youtoy.domain.like.entity.Dislike;
import com.pellto.youtoy.domain.like.repository.DislikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DislikeReadService {

  private final DislikeRepository dislikeRepository;

  public LikeDto getByCreateCmd(CreateLikeCommand cmd) {
    var dislike = dislikeRepository.findByCreateCmd(cmd).orElse(null);
    return dislike == null ? null : toDto(dislike);
  }

  private LikeDto toDto(Dislike dislike) {
    return new LikeDto(
        dislike.getId(),
        dislike.getVideoId(),
        dislike.getVideoType(),
        dislike.getCommentId(),
        dislike.getUserId()
    );
  }
}
