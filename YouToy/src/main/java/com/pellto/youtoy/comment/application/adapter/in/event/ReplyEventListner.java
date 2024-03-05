package com.pellto.youtoy.comment.application.adapter.in.event;

import com.pellto.youtoy.comment.domain.port.in.CommentReplyUsecase;
import com.pellto.youtoy.global.event.comment.ReplyRemovedEvent;
import com.pellto.youtoy.global.event.comment.ReplyWrittenEvent;
import com.pellto.youtoy.global.interfaces.InboundEventAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;

@InboundEventAdapter("ReplyEventListnerInComment")
@RequiredArgsConstructor
@Slf4j
public class ReplyEventListner {

  private static final String LISTENER = "ReplyEventListner";
  private final CommentReplyUsecase commentReplyUsecase;

  @EventListener
  public void increaseReplyCountByReplyWritten(ReplyWrittenEvent event) {
    var listener = LISTENER + "increaseReplyCountByReplyWritten";
    log.info(String.format("[%s]: 댓글 답글 수 증가 시작(by %s) {dto: %s}",
        listener, event.getPublisher(), event.getDto()));

    commentReplyUsecase.increaseCommentReplyCount(event.getDto().parentCommentId());
  }

  @EventListener
  public void decreaseReplyCountByReplyRemoved(ReplyRemovedEvent event) {
    var listener = LISTENER + "decreaseReplyCountByReplyRemoved";
    log.info(String.format("[%s]: 댓글 답글 수 감소 시작(by %s) {replyId: %s}",
        listener, event.getPublisher(), event.getReplyId()));

    commentReplyUsecase.decreaseCommentReplyCount(event.getParentCommentId());
  }
}
