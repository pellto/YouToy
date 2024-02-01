package com.pellto.youtoy.post.application.adapter.out.event;

import com.pellto.youtoy.global.event.post.PostRemovedEvent;
import com.pellto.youtoy.global.interfaces.OutboundEventAdapter;
import com.pellto.youtoy.post.domain.port.out.PostEventPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

@OutboundEventAdapter
@RequiredArgsConstructor
@Slf4j
public class PostEventPublisher implements PostEventPort {

  private static final String PUBLISHER = "PostEventPublisher";
  private final ApplicationEventPublisher applicationEventPublisher;

  @Override
  public void postRemovedEvent(Long postId) {
    var publisher = PUBLISHER + "/postRemovedEvent";
    log.info(String.format("[%s]: 게시글{postId: %s} 삭제 완료", publisher, postId));

    var event = new PostRemovedEvent(postId, publisher);
    applicationEventPublisher.publishEvent(event);
  }
}
