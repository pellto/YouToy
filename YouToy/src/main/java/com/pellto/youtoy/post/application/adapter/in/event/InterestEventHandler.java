package com.pellto.youtoy.post.application.adapter.in.event;

import com.pellto.youtoy.global.event.interest.DeletedLikeEvent;
import com.pellto.youtoy.global.event.interest.LikeEvent;
import com.pellto.youtoy.global.interfaces.InboundEventAdapter;
import com.pellto.youtoy.post.domain.port.in.InterestActionUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;

@InboundEventAdapter("InterestEventHandlerInPost")
@RequiredArgsConstructor
@Slf4j
public class InterestEventHandler {

  private static final String HANDLER = "InterestEventHandler";
  private final InterestActionUsecase interestActionUsecase;

  @EventListener
  public void increaseLikeCount(LikeEvent event) throws InterruptedException {
    if (!event.getDto().contentsType().equals("POST")) {
      return;
    }
    log.info(
        String.format("[%s/increaseLikeCount]: 포스트 좋아요 증가 시작(by %s) {dto: %s}", HANDLER,
            event.getPublisher(), event.getPublisher())
    );
    interestActionUsecase.increaseLikeCount(event.getDto().contentsId());
    log.info(
        String.format("[%s/increaseLikeCount]: 포스트 좋아요 증가 완료(by %s) {dto: %s}", HANDLER,
            event.getPublisher(), event.getPublisher())
    );
  }

  @EventListener
  public void decreaseLikeCount(DeletedLikeEvent event) throws InterruptedException {
    if (!event.getDto().contentsType().equals("POST")) {
      return;
    }
    log.info(
        String.format("[%s/increaseLikeCount]: 포스트 좋아요 감소 시작(by %s) {dto: %s}", HANDLER,
            event.getPublisher(), event.getPublisher())
    );
    interestActionUsecase.decreaseLikeCount(event.getDto().contentsId());
    log.info(
        String.format("[%s/increaseLikeCount]: 포스트 좋아요 감소 완료(by %s) {dto: %s}", HANDLER,
            event.getPublisher(), event.getPublisher())
    );
  }
}
