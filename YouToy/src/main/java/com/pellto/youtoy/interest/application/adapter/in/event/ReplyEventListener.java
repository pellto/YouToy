package com.pellto.youtoy.interest.application.adapter.in.event;


import com.pellto.youtoy.global.event.comment.ReplyRemovedEvent;
import com.pellto.youtoy.global.interfaces.InboundEventAdapter;
import com.pellto.youtoy.interest.domain.port.in.ReplyEventActions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;

@InboundEventAdapter("ReplyEventListenerInInterest")
@RequiredArgsConstructor
@Slf4j
public class ReplyEventListener {

  private static final String LISTENER = "ReplyEventListener";
  private final ReplyEventActions replyEventActions;

  @EventListener
  public void removeInterestsByReplyRemoved(ReplyRemovedEvent event) {
    var listener = LISTENER + "/increaseReplyCount";

    log.info(
        String.format("[%s]: 답글 좋아요 삭제 시작(by %s) {replyId: %s}",
            listener, event.getPublisher(), event.getReplyId())
    );

    replyEventActions.removeAllByReplyId(event.getReplyId());
    log.info(
        String.format("[%s]: 답글 좋아요 삭제 완료(by %s) {replyId: %s}",
            listener, event.getPublisher(), event.getReplyId())
    );
  }
}
