package com.pellto.youtoy.domain.comment.service;

import com.pellto.youtoy.domain.comment.dto.CreateMentionCommand;
import com.pellto.youtoy.domain.comment.entity.Mention;
import com.pellto.youtoy.domain.comment.repository.MentionRepository;
import com.pellto.youtoy.util.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@RequiredArgsConstructor
@Service
public class MentionWriteService {

  private final MentionRepository mentionRepository;

  public Mention create(CreateMentionCommand cmd) {
    var mention = Mention.builder()
        .commentId(cmd.commentId())
        .mentionedChannelId(cmd.mentionedChannelId())
        .createdAt(cmd.createdAt())
        .build();
    return mentionRepository.save(mention);
  }

  public void deleteByCommentId(Long commentId) {
    Assert.isTrue(mentionRepository.existByCommentId(commentId),
        ErrorCode.NOT_EXIST_MENTION.getMessage());
    mentionRepository.deleteByCommentId(commentId);
  }
}
