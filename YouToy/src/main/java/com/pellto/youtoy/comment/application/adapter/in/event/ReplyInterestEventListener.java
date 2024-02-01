package com.pellto.youtoy.comment.application.adapter.in.event;

import com.pellto.youtoy.comment.domain.port.in.ReplyInterestEventActions;
import com.pellto.youtoy.global.event.interest.DeletedLikeEvent;
import com.pellto.youtoy.global.event.interest.LikeEvent;
import com.pellto.youtoy.global.interfaces.InboundEventAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;

@InboundEventAdapter("ReplyInterestEventListenerInReply")
@RequiredArgsConstructor
@Slf4j
public class ReplyInterestEventListener {

  private static final String LISTENER = "ReplyInterestEventListener";
  private final ReplyInterestEventActions replyInterestEventActions;

  @EventListener
  public void increaseLikeCount(LikeEvent event) {
    if (!event.getDto().interestContentsType().equals("REPLY")) {
      return;
    }

    var listener = LISTENER + "increaseLikeCount";
    log.info(String.format("[%s]: 답글 좋아요 증가 시작(by %s) {dto: %s}",
        listener, event.getPublisher(), event.getDto()));

    replyInterestEventActions.increaseLikeCount(event.getDto().contentsId());
  }

  @EventListener
  public void decreaseLikeCount(DeletedLikeEvent event) {
    if (!event.getDto().interestContentsType().equals("REPLY")) {
      return;
    }

    var listener = LISTENER + "decreaseLikeCount";
    log.info(String.format("[%s]: 답글 좋아요 감소 시작(by %s) {dto: %s}",
        listener, event.getPublisher(), event.getDto()));

    replyInterestEventActions.decreaseLikeCount(event.getDto().contentsId());
  }
}
