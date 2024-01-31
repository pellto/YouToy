package com.pellto.youtoy.global.event.interest;

import com.pellto.youtoy.global.dto.interest.InterestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class DeletedLikeEvent {

  private InterestDto dto;
  private String publisher;
}
