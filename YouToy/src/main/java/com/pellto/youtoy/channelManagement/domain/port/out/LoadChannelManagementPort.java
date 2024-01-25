package com.pellto.youtoy.channelManagement.domain.port.out;

import com.pellto.youtoy.channelManagement.domain.model.ChannelManagement;

public interface LoadChannelManagementPort {

  ChannelManagement loadByChannelIdAndMemberId(Long channelId, Long memberId);

}
