package com.pellto.youtoy.comment.domain.port.in;

import com.pellto.youtoy.global.dto.comment.ReplyDto;
import java.util.List;

public interface ReadReplyUsecase {

  List<ReplyDto> readAllByParentCommentId(Long parentCommentId);
}
