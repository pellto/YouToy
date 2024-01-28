package com.pellto.youtoy.auth.domain.model;


import com.pellto.youtoy.global.dto.auth.AuthDto;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;

// TODO: Define Auth POJO
@Getter
public class Auth {

  private final Long memberId;
  private final String email;
  private final ChannelRoles channelRoles;
  private final MemberRoles memberRoles;
  private final String token;

  @Builder
  public Auth(Long memberId, String email, ChannelRoles channelRoles, MemberRoles memberRoles,
      String token) {
    this.memberId = Objects.requireNonNull(memberId);
    this.email = Objects.requireNonNull(email);
    this.channelRoles = Objects.requireNonNull(channelRoles);
    this.memberRoles = Objects.requireNonNull(memberRoles);
    this.token = token;
  }

  public AuthDto toDto() {
    return new AuthDto(memberId, email, channelRoles.getRoles(), memberRoles.getRoles(), token);
  }
}
