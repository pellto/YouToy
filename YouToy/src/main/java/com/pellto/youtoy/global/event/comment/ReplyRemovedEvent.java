package com.pellto.youtoy.global.event.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class ReplyRemovedEvent {

  private Long replyId;
  private Long parentCommentId;
  private String publisher;

}
