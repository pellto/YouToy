package com.pellto.youtoy.channelManagement.domain.port.in;

import com.pellto.youtoy.global.dto.channelManagement.response.ChangeChannelManagementResponse;

public interface ChangeManagementUsecase {

  ChangeChannelManagementResponse changeLevel(Long channelId, Long memberId, String level);
}
