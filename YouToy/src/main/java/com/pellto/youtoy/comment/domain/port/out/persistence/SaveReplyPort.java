package com.pellto.youtoy.comment.domain.port.out.persistence;

import java.util.List;

import com.pellto.youtoy.comment.domain.model.Reply;

public interface SaveReplyPort {

  void deleteById(Long replyId);

  void deleteAllByParentCommentId(Long parentCommentId);

  void deleteAllByParentCommentIdIn(List<Long> parentCommentIds);

  Reply save(Reply reply);

  void update(Reply reply);
}
