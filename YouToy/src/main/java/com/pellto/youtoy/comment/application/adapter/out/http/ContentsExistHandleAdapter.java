package com.pellto.youtoy.comment.application.adapter.out.http;

import com.pellto.youtoy.comment.domain.port.out.http.ContentsExistHandlePort;
import com.pellto.youtoy.comment.domain.port.out.http.PostHandlePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContentsExistHandleAdapter implements ContentsExistHandlePort {

  private final PostHandlePort postHandlePort;

  @Override
  public boolean isExistContents(String contentsType, Long contentsId) {
    switch (contentsType) {
      case ("POST") -> {
        return postHandlePort.isExistPost(contentsId);
      }
      default -> {
        throw new IllegalArgumentException("적절한 컨텐츠 타입이 아닙니다.");
      }
    }
  }
}
