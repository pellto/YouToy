package com.pellto.youtoy.auth.domain.port.out;

import com.pellto.youtoy.global.dto.channelManagement.ChannelManagementDto;
import java.util.List;

public interface ChannelManagementHandlePort {

  List<ChannelManagementDto> getChannelManagement(Long memberId);
}
