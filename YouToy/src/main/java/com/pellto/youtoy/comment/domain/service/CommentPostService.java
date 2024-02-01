package com.pellto.youtoy.comment.domain.service;

import com.pellto.youtoy.comment.domain.model.CommentContentsType;
import com.pellto.youtoy.comment.domain.port.in.PostEventActions;
import com.pellto.youtoy.comment.domain.port.out.event.CommentEventPort;
import com.pellto.youtoy.comment.domain.port.out.persistence.LoadCommentPort;
import com.pellto.youtoy.comment.domain.port.out.persistence.SaveCommentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentPostService implements PostEventActions {

  private static final CommentContentsType COMMENT_CONTENTS_TYPE = CommentContentsType.POST;
  private final SaveCommentPort saveCommentPort;
  private final LoadCommentPort loadCommentPort;
  private final CommentEventPort commentEventPort;

  @Override
  public void removeAllByPostId(Long postId) {
    var willRemoveCommentIds = loadCommentPort.loadAllIdsByContentsTypeAndContentsId(
        COMMENT_CONTENTS_TYPE.getType(), postId);
    saveCommentPort.deleteAllByContentsIdAndContentsType(postId, COMMENT_CONTENTS_TYPE);
    willRemoveCommentIds.forEach(commentEventPort::commentRemovedEvent);
  }
}
