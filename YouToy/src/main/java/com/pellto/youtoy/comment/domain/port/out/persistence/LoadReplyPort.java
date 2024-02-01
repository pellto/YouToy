package com.pellto.youtoy.comment.domain.port.out.persistence;

import com.pellto.youtoy.comment.domain.model.Reply;
import java.util.List;

public interface LoadReplyPort {

  Reply load(Long replyId);

  List<Reply> loadAllByParentCommentId(Long parentCommentId);

  List<Long> loadAllIdsByParentCommentId(Long parentCommentId);
}
