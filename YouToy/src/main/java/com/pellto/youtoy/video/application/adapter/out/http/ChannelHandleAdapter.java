package com.pellto.youtoy.video.application.adapter.out.http;

import com.pellto.youtoy.video.domain.port.out.ChannelHandlePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("ChannelHandleAdapterInVideo")
@RequiredArgsConstructor
public class ChannelHandleAdapter implements ChannelHandlePort {

  private final RestTemplateAdapter restTemplateAdapter;
  private static final String DOMAIN = "http://127.0.0.1";
  private static final String PORT = "8080";
  private static final String ROOT_ROUTE = "channels";

  @Override
  public boolean existByChannelId(Long channelId) {
    String url = String.format("%s:%s/%s/exist/%s", DOMAIN, PORT, ROOT_ROUTE, channelId);
    return restTemplateAdapter.getForObject(url, boolean.class);
  }
}
