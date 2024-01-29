package com.pellto.youtoy.auth.application.adapter.out.http;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class RestTemplateAdapter {

  private final RestTemplate restTemplate = new RestTemplate();

  public <T> T getForObject(String url, Class<T> responseType, Object... uriVariables)
      throws RestClientException {
    return restTemplate.getForObject(url, responseType, uriVariables);
  }
}
