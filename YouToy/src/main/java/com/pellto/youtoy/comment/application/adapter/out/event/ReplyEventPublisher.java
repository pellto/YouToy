package com.pellto.youtoy.comment.application.adapter.out.event;

import com.pellto.youtoy.comment.domain.port.out.event.ReplyEventPort;
import com.pellto.youtoy.global.dto.comment.ReplyDto;
import com.pellto.youtoy.global.event.comment.ReplyRemovedEvent;
import com.pellto.youtoy.global.event.comment.ReplyWrittenEvent;
import com.pellto.youtoy.global.interfaces.OutboundEventAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

@OutboundEventAdapter
@RequiredArgsConstructor
@Slf4j
public class ReplyEventPublisher implements ReplyEventPort {

  private static final String PUBLISHER = "ReplyEventPublisher";
  private final ApplicationEventPublisher applicationEventPublisher;

  @Override
  public void replyWrittenEvent(ReplyDto dto) {
    var publisher = PUBLISHER + "/replyWrittenEvent";
    log.info(String.format("[%s]: 답글 생성 완료 {dto: %s}", publisher, dto));

    var event = new ReplyWrittenEvent(dto, publisher);
    applicationEventPublisher.publishEvent(event);
  }

  @Override
  public void replyRemovedEvent(Long replyId, Long parentCommentId) {
    var publisher = PUBLISHER + "/replyRemovedEvent";
    log.info(String.format("[%s]: 답글 삭제 완료 {replyId: %s}", publisher, replyId));

    var event = new ReplyRemovedEvent(replyId, parentCommentId, publisher);
    applicationEventPublisher.publishEvent(event);
  }

  @Override
  public void replyChangedEvent(ReplyDto before, ReplyDto after) {
    throw new UnsupportedOperationException("Unimplemented method 'replyChangedEvent'");
  }
}
