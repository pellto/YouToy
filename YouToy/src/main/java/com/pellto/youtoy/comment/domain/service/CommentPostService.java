package com.pellto.youtoy.comment.domain.service;

import com.pellto.youtoy.comment.domain.model.CommentContentsType;
import com.pellto.youtoy.comment.domain.port.in.PostEventActions;
import com.pellto.youtoy.comment.domain.port.out.persistence.SaveCommentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentPostService implements PostEventActions {

  private static final CommentContentsType COMMENT_CONTENTS_TYPE = CommentContentsType.POST;
  private final SaveCommentPort saveCommentPort;

  @Override
  public void removeAllByPostId(Long postId) {
    saveCommentPort.deleteAllByContentsIdAndContentsType(postId, COMMENT_CONTENTS_TYPE);
  }
}
