package com.pellto.youtoy.comment.application.adapter.out.http;

import com.pellto.youtoy.comment.domain.port.out.http.ChannelHandlePort;
import com.pellto.youtoy.global.dto.channel.response.GetCommenterChannelInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("ChannelHandleAdapterInComment")
@RequiredArgsConstructor
public class ChannelHandleAdapter implements ChannelHandlePort {

  private final RestTemplateAdapter restTemplateAdapter;
  private static final String DOMAIN = "http://127.0.0.1";
  private static final String PORT = "8080";
  private static final String ROOT_ROUTE = "channels";


  @Override
  public GetCommenterChannelInfoResponse getCommenterChannelInfo(Long channelId) {
    String url = String.format("%s:%s/%s/info/%s", DOMAIN, PORT, ROOT_ROUTE, channelId);
    return restTemplateAdapter.getForObject(url, GetCommenterChannelInfoResponse.class);
  }
}
