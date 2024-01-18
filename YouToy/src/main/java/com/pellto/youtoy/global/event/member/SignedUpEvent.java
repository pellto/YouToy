package com.pellto.youtoy.global.event.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class SignedUpEvent {

  private Long memberId;
  private String memberUuid;
  private Long membershipId;
}
