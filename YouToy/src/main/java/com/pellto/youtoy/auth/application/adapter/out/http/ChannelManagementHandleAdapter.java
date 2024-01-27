package com.pellto.youtoy.auth.application.adapter.out.http;

import com.pellto.youtoy.auth.domain.port.out.ChannelManagementHandlePort;
import com.pellto.youtoy.global.dto.channelManagement.ChannelManagementDto;
import com.pellto.youtoy.global.dto.channelManagement.response.GetChannelManagementByMemberIdResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class ChannelManagementHandleAdapter implements ChannelManagementHandlePort {

  private static final String DOMAIN = "http://127.0.0.1";
  private static final String PORT = "8080";
  private static final String ROOT_ROUTE = "channelManagements";


  // TODO: change restTemplate to feignClient
  @Override
  public List<ChannelManagementDto> getChannelManagement(Long memberId) {
    String url = String.format("%s:%s/%s/member/%s", DOMAIN, PORT, ROOT_ROUTE, memberId);
    var restTemplate = new RestTemplate();
    var response = restTemplate.getForObject(url, GetChannelManagementByMemberIdResponse.class);
    return response.channelManagements();
  }
}
