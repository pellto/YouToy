package com.pellto.youtoy.comment.domain.port.out.persistence;

import com.pellto.youtoy.comment.domain.model.Comment;

public interface LoadCommentPort {

  Comment load(Long commentId);
}
