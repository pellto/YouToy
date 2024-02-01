package com.pellto.youtoy.comment.domain.port.out.persistence;

import com.pellto.youtoy.comment.domain.model.Reply;

public interface SaveReplyPort {

  void deleteById(Long replyId);

  Reply save(Reply reply);

  void update(Reply reply);
}
