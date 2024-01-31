package com.pellto.youtoy.global.event.comment;

import com.pellto.youtoy.global.dto.comment.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class CommentWrittenEvent {

  private CommentDto dto;
  private String publisher;

}
