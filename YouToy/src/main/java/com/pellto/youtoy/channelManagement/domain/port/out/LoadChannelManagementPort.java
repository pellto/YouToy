package com.pellto.youtoy.channelManagement.domain.port.out;

import com.pellto.youtoy.channelManagement.domain.model.ChannelManagement;
import java.util.List;

public interface LoadChannelManagementPort {

  ChannelManagement loadByChannelIdAndMemberId(Long channelId, Long memberId);

  List<ChannelManagement> loadByMemberId(Long memberId);
}
