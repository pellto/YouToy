package com.pellto.youtoy.auth.application.adapter.out.http;

import com.pellto.youtoy.auth.domain.port.out.MemberInfoHandleUsecase;
import com.pellto.youtoy.global.dto.member.MemberInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class MemberInfoHandleAdapter implements MemberInfoHandleUsecase {

  private static final String DOMAIN = "http://127.0.0.1";
  private static final String PORT = "8080";
  private static final String ROOT_ROUTE = "members";

  // TODO: change restTemplate to feignClient
  @Override
  public MemberInfoDto getMemberInfo(String email) {
    String url = String.format("%s:%s/%s/info/%s", DOMAIN, PORT, ROOT_ROUTE, email);
    var restTemplate = new RestTemplate();
    return restTemplate.getForObject(url, MemberInfoDto.class);
  }
}
