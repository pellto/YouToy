package com.pellto.youtoy.comment.domain.port.in;

import com.pellto.youtoy.global.dto.comment.ReplyDto;
import com.pellto.youtoy.global.dto.comment.request.WriteReplyRequest;

public interface WriteReplyUsecase {

  ReplyDto writeReply(WriteReplyRequest request);

  void removeReply(Long replyId);
}
