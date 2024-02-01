package com.pellto.youtoy.interest.application.adapter.out.http;

import com.pellto.youtoy.interest.domain.port.out.http.PostHandlePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("PostHandleAdapterInInterest")
@RequiredArgsConstructor
public class PostHandleAdapter implements PostHandlePort {

  private final RestTemplateAdapter restTemplateAdapter;
  private static final String DOMAIN = "http://127.0.0.1";
  private static final String PORT = "8080";
  private static final String ROOT_ROUTE = "posts";

  @Override
  public boolean isExistPost(Long postId) {
    String url = String.format("%s:%s/%s/exist/%s", DOMAIN, PORT, ROOT_ROUTE, postId);
    return restTemplateAdapter.getForObject(url, boolean.class);
  }
}
