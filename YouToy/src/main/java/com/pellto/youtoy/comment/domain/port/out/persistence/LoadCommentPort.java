package com.pellto.youtoy.comment.domain.port.out.persistence;

import com.pellto.youtoy.comment.domain.model.Comment;
import java.util.List;

public interface LoadCommentPort {

  Comment load(Long commentId);

  List<Comment> loadAllByContentsTypeAndContentsId(
      String commentContentsType, Long contentsId);
}
