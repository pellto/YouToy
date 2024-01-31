package com.pellto.youtoy.channel.domain.port.in;

import com.pellto.youtoy.global.dto.channel.ChannelDto;
import com.pellto.youtoy.global.dto.channel.response.GetCommenterChannelInfoResponse;

public interface GetChannelInfoUsecase {

  ChannelDto getChannelById(Long channelId);

  GetCommenterChannelInfoResponse getCommenterChannelInfoById(Long channelId);
}
