package com.pellto.youtoy.global.event.membership;

import com.pellto.youtoy.global.dto.membership.MembershipDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class MembershipRemovedEvent {

  private MembershipDto dto;
  private String publisher;
}
