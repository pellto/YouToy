package com.pellto.youtoy.global.event.comment;

import com.pellto.youtoy.global.dto.comment.ReplyDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class ReplyWrittenEvent {

  private ReplyDto dto;
  private String publisher;

}
