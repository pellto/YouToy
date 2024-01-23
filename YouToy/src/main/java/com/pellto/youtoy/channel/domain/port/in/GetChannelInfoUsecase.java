package com.pellto.youtoy.channel.domain.port.in;

import com.pellto.youtoy.global.dto.channel.ChannelDto;

public interface GetChannelInfoUsecase {

  ChannelDto getChannelById(Long channelId);
}
