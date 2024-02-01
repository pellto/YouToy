package com.pellto.youtoy.global.event.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class CommentRemovedEvent {

  private Long commentId;
  private String publisher;

}
