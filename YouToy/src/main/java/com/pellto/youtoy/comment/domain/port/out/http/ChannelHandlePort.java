package com.pellto.youtoy.comment.domain.port.out.http;

import com.pellto.youtoy.global.dto.channel.response.GetCommenterChannelInfoResponse;

public interface ChannelHandlePort {

  GetCommenterChannelInfoResponse getCommenterChannelInfo(Long channelId);
}
