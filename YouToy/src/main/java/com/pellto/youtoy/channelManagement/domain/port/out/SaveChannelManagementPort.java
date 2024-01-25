package com.pellto.youtoy.channelManagement.domain.port.out;

import com.pellto.youtoy.channelManagement.domain.model.ChannelManagement;

public interface SaveChannelManagementPort {

  void delete(ChannelManagement management);

  ChannelManagement save(ChannelManagement management);
}
