package com.pellto.youtoy.channelManagement.domain.port.out;

import com.pellto.youtoy.global.dto.channelManagement.ChannelManagementDto;

public interface ChannelManagementEventPort {

  void channelManagementChangedEvent(ChannelManagementDto before, ChannelManagementDto after);
}
