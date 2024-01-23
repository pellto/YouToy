package com.pellto.youtoy.member.application.adapter.out.http;

import com.pellto.youtoy.global.dto.membership.MembershipDto;
import com.pellto.youtoy.member.domain.port.out.MembershipHandlePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class MembershipAdapter implements MembershipHandlePort {

  @Override
  public MembershipDto getMembershipInfo(Long id) {
    String url = String.format("http://127.0.0.1:8080/memberships/%s", id);
    var restTemplate = new RestTemplate();
    return restTemplate.getForObject(url, MembershipDto.class);
  }
}
