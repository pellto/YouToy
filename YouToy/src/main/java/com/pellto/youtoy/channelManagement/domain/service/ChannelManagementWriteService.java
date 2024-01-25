package com.pellto.youtoy.channelManagement.domain.service;

import com.pellto.youtoy.channelManagement.domain.model.ChannelManagement;
import com.pellto.youtoy.channelManagement.domain.model.ChannelManagementLevel;
import com.pellto.youtoy.channelManagement.domain.port.in.ChangeManagementUsecase;
import com.pellto.youtoy.channelManagement.domain.port.in.CreateManagementUsecase;
import com.pellto.youtoy.channelManagement.domain.port.out.ChannelManagementEventPort;
import com.pellto.youtoy.channelManagement.domain.port.out.LoadChannelManagementPort;
import com.pellto.youtoy.channelManagement.domain.port.out.SaveChannelManagementPort;
import com.pellto.youtoy.global.dto.channelManagement.ChannelManagementDto;
import com.pellto.youtoy.global.dto.channelManagement.response.ChangeChannelManagementResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class ChannelManagementWriteService implements CreateManagementUsecase,
    ChangeManagementUsecase {

  private final SaveChannelManagementPort saveChannelManagementPort;
  private final LoadChannelManagementPort loadChannelManagementPort;
  private final ChannelManagementEventPort channelManagementEventPort;

  @Override
  public ChangeChannelManagementResponse changeLevel(Long channelId, Long memberId, String level) {
    var management = loadChannelManagementPort.loadByChannelIdAndMemberId(channelId, memberId);
    var before = management.toDto();

    management.changeManageLevel(ChannelManagementLevel.fromString(level));
    saveChannelManagementPort.save(management);

    var after = management.toDto();
    channelManagementEventPort.channelManagementChangedEvent(before, after);
    return new ChangeChannelManagementResponse(before, after);
  }

  @Override
  public ChannelManagementDto invite(Long channelId, Long memberId) {
    var channelManagement = ChannelManagement.builder()
        .channelId(channelId)
        .memberId(memberId)
        .build();

    channelManagement = saveChannelManagementPort.save(channelManagement);
    return channelManagement.toDto();
  }

  @Override
  public ChannelManagementDto createOwner(Long channelId, Long memberId) {
    var channelManagement = ChannelManagement.builder()
        .channelId(channelId)
        .memberId(memberId)
        .manageLevel(ChannelManagementLevel.OWNER)
        .build();

    channelManagement = saveChannelManagementPort.save(channelManagement);
    return channelManagement.toDto();
  }
}
