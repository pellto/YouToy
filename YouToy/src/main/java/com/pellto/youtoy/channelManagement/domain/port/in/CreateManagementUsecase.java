package com.pellto.youtoy.channelManagement.domain.port.in;

import com.pellto.youtoy.global.dto.channelManagement.ChannelManagementDto;

public interface CreateManagementUsecase {

  ChannelManagementDto invite(Long channelId, Long memberId);

  ChannelManagementDto createOwner(Long channelId, Long memberId);
}
