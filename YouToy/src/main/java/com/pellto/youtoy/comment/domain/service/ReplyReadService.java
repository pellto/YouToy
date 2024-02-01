package com.pellto.youtoy.comment.domain.service;

import com.pellto.youtoy.comment.domain.model.Reply;
import com.pellto.youtoy.comment.domain.port.in.ReadReplyUsecase;
import com.pellto.youtoy.comment.domain.port.out.persistence.LoadReplyPort;
import com.pellto.youtoy.global.dto.comment.ReplyDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class ReplyReadService implements ReadReplyUsecase {

  private final LoadReplyPort loadReplyPort;

  @Override
  public List<ReplyDto> readAllByParentCommentId(Long parentCommentId) {
    var replies = loadReplyPort.loadAllByParentCommentId(parentCommentId);
    return replies.stream().map(Reply::toDto).toList();
  }
}
