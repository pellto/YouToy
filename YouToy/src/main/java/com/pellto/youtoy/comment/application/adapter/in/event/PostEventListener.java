package com.pellto.youtoy.comment.application.adapter.in.event;

import com.pellto.youtoy.comment.domain.port.in.PostEventActions;
import com.pellto.youtoy.global.event.post.PostRemovedEvent;
import com.pellto.youtoy.global.interfaces.InboundEventAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;

@InboundEventAdapter("PostEventListenerInComment")
@RequiredArgsConstructor
@Slf4j
public class PostEventListener {

  private static final String LISTENER = "PostEventListener";
  private final PostEventActions postEventActions;

  @EventListener
  public void removeCommentsByPostRemoved(PostRemovedEvent event) {
    var listener = LISTENER + "removeCommentsByPostRemoved";
    log.info(String.format("[%s]: 포스트 댓글 삭제 시작(by %s) {postId: %s}",
        listener, event.getPublisher(), event.getPostId()));

    postEventActions.removeAllByPostId(event.getPostId());
  }

}
