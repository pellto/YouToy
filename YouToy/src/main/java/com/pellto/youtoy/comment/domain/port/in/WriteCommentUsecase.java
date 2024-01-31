package com.pellto.youtoy.comment.domain.port.in;

import com.pellto.youtoy.global.dto.comment.CommentDto;
import com.pellto.youtoy.global.dto.comment.request.WriteCommentRequest;

public interface WriteCommentUsecase {

  CommentDto write(WriteCommentRequest request);
}
