package com.pellto.youtoy.global.event.member;

import com.pellto.youtoy.global.dto.member.MemberInfoDto;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class RequestedSignUpEvent {

  private MemberInfoDto memberInfoDto;
  private LocalDateTime requiredAt;
}
