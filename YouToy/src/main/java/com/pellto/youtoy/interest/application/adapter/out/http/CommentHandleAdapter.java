package com.pellto.youtoy.interest.application.adapter.out.http;

import com.pellto.youtoy.interest.domain.port.out.http.CommentHandlePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("CommentHandleAdapterInInterest")
@RequiredArgsConstructor
public class CommentHandleAdapter implements CommentHandlePort {

  private final RestTemplateAdapter restTemplateAdapter;
  private static final String DOMAIN = "http://127.0.0.1";
  private static final String PORT = "8080";
  private static final String ROOT_ROUTE = "comments";

  @Override
  public boolean isExistComment(Long commentId) {
    String url = String.format("%s:%s/%s/exist/%s", DOMAIN, PORT, ROOT_ROUTE, commentId);
    return restTemplateAdapter.getForObject(url, boolean.class);
  }
}
