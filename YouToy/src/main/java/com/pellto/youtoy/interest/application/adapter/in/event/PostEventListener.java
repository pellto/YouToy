package com.pellto.youtoy.interest.application.adapter.in.event;

import com.pellto.youtoy.global.event.post.PostRemovedEvent;
import com.pellto.youtoy.global.interfaces.InboundEventAdapter;
import com.pellto.youtoy.interest.domain.port.in.PostEventActions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;

@InboundEventAdapter("PostEventListenerInInterest")
@RequiredArgsConstructor
@Slf4j
public class PostEventListener {

  private static final String LISTENER = "PostEventListener";
  private final PostEventActions postEventActions;

  @EventListener
  public void removeInterestsByPostRemoved(PostRemovedEvent event) {
    var listener = LISTENER + "removeInterestsByPostRemoved";
    log.info(String.format("[%s]: 포스트 interest 삭제 시작(by %s) {postId: %s}",
        listener, event.getPublisher(), event.getPostId()));

    postEventActions.removeAllByPostId(event.getPostId());
  }

}
