package com.pellto.youtoy.channelManagement.domain.service;

import com.pellto.youtoy.channelManagement.domain.model.ChannelManagement;
import com.pellto.youtoy.channelManagement.domain.port.in.GetChannelManagementUsecase;
import com.pellto.youtoy.channelManagement.domain.port.out.LoadChannelManagementPort;
import com.pellto.youtoy.global.dto.channelManagement.response.GetChannelManagementByMemberIdResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChannelManagementReadService implements GetChannelManagementUsecase {

  private final LoadChannelManagementPort loadChannelManagementPort;

  @Override
  public GetChannelManagementByMemberIdResponse getByMemberId(Long memberId) {
    var channelManagements = loadChannelManagementPort.loadByMemberId(memberId);
    var channelManagementDtos = channelManagements.stream().map(ChannelManagement::toDto).toList();
    return new GetChannelManagementByMemberIdResponse(channelManagementDtos);
  }
}
