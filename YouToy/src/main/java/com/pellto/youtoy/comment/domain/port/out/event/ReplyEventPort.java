package com.pellto.youtoy.comment.domain.port.out.event;

import com.pellto.youtoy.global.dto.comment.ReplyDto;

public interface ReplyEventPort {

  void replyWrittenEvent(ReplyDto dto);

  void replyRemovedEvent(Long id);

  void replyChangedEvent(ReplyDto before, ReplyDto after);
}
