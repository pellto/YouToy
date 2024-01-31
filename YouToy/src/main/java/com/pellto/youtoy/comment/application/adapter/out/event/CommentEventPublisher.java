package com.pellto.youtoy.comment.application.adapter.out.event;

import com.pellto.youtoy.comment.domain.port.out.event.CommentEventPort;
import com.pellto.youtoy.global.dto.comment.CommentDto;
import com.pellto.youtoy.global.event.comment.CommentChangedEvent;
import com.pellto.youtoy.global.event.comment.CommentWrittenEvent;
import com.pellto.youtoy.global.interfaces.OutboundEventAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

@OutboundEventAdapter
@RequiredArgsConstructor
@Slf4j
public class CommentEventPublisher implements CommentEventPort {

  private static final String PUBLISHER = "CommentEventPublisher";
  private final ApplicationEventPublisher applicationEventPublisher;

  @Override
  public void commentWrittenEvent(CommentDto dto) {
    var publisher = PUBLISHER + "/commentWrittenEvent";
    log.info(String.format("[%s]: 댓글 생성 완료 {dto: %s}", publisher, dto));

    var event = new CommentWrittenEvent(dto, publisher);
    applicationEventPublisher.publishEvent(event);
  }

  @Override
  public void commentRemovedEvent(CommentDto dto) {

  }

  @Override
  public void commentChangedEvent(CommentDto before, CommentDto after) {
    var publisher = PUBLISHER + "/commentChangedEvent";
    log.info(String.format("[%s]: 댓글 변경 완료 {before: %s, after: %s}", publisher, before, after));

    var event = new CommentChangedEvent(before, after, publisher);
    applicationEventPublisher.publishEvent(event);
  }
}
