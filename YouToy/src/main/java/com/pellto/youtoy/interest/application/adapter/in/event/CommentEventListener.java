package com.pellto.youtoy.interest.application.adapter.in.event;


import com.pellto.youtoy.global.event.comment.CommentRemovedEvent;
import com.pellto.youtoy.global.interfaces.InboundEventAdapter;
import com.pellto.youtoy.interest.domain.port.in.CommentEventActions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;

@InboundEventAdapter("CommentEventListenerInInterest")
@RequiredArgsConstructor
@Slf4j
public class CommentEventListener {

  private static final String LISTENER = "CommentEventListener";
  private final CommentEventActions commentEventActions;

  @EventListener
  public void removeInterestsByCommentRemoved(CommentRemovedEvent event) {
    var listener = LISTENER + "/increaseCommentCount";

    log.info(
        String.format("[%s]: 댓글 좋아요 삭제 시작(by %s) {commentId: %s}",
            listener, event.getPublisher(), event.getCommentId())
    );

    commentEventActions.removeAllByCommentId(event.getCommentId());
    log.info(
        String.format("[%s]: 댓글 좋아요 삭제 완료(by %s) {commentId: %s}",
            listener, event.getPublisher(), event.getCommentId())
    );
  }
}
