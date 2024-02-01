package com.pellto.youtoy.global.event.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class PostRemovedEvent {

  private Long postId;
  private String publisher;
}
