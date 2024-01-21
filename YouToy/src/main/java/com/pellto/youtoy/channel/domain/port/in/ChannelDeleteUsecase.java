package com.pellto.youtoy.channel.domain.port.in;

import com.pellto.youtoy.global.dto.member.MemberDto;

public interface ChannelDeleteUsecase {

  void delete(MemberDto dto);
}
