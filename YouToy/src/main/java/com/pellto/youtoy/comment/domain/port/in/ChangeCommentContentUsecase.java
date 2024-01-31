package com.pellto.youtoy.comment.domain.port.in;

import com.pellto.youtoy.global.dto.comment.CommentDto;
import com.pellto.youtoy.global.dto.comment.request.ChangeCommentRequest;

public interface ChangeCommentContentUsecase {

  CommentDto change(ChangeCommentRequest request);
}
