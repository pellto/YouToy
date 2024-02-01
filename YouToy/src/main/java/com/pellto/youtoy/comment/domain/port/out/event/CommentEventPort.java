package com.pellto.youtoy.comment.domain.port.out.event;

import com.pellto.youtoy.global.dto.comment.CommentDto;

public interface CommentEventPort {

  void commentWrittenEvent(CommentDto dto);

  void commentRemovedEvent(Long id);

  void commentChangedEvent(CommentDto before, CommentDto after);
}
