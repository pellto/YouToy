package com.pellto.youtoy.post.application.adapter.in.event;


import com.pellto.youtoy.global.event.comment.CommentWrittenEvent;
import com.pellto.youtoy.global.interfaces.InboundEventAdapter;
import com.pellto.youtoy.post.domain.port.in.CommentEventActions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;

@InboundEventAdapter("CommentEventListenerInPost")
@RequiredArgsConstructor
@Slf4j
public class CommentEventListener {

  private static final String LISTENER = "CommentEventListener";
  private final CommentEventActions commentEventActions;

  @EventListener
  public void increaseCommentCount(CommentWrittenEvent event) {
    if (!event.getDto().commentContentsType().equals("POST")) {
      return;
    }
    var listener = LISTENER + "/increaseCommentCount";

    log.info(
        String.format("[%s]: 포스트 댓글 수 증가 시작(by %s) {dto: %s}",
            listener, event.getPublisher(), event.getDto())
    );

    commentEventActions.increaseCommentCount(event);
    log.info(
        String.format("[%s]: 포스트 댓글 수 증가 완료(by %s) {dto: %s}",
            listener, event.getPublisher(), event.getDto())
    );
  }
}
