package com.pellto.youtoy.global.event.member;

import com.pellto.youtoy.global.dto.member.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class MemberDeletedEvent {

  private MemberDto dto;
  private String publisher;
}
