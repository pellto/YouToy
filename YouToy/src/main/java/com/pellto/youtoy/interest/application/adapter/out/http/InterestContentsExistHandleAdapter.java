package com.pellto.youtoy.interest.application.adapter.out.http;

import com.pellto.youtoy.interest.domain.port.out.http.CommentHandlePort;
import com.pellto.youtoy.interest.domain.port.out.http.InterestContentsExistHandlePort;
import com.pellto.youtoy.interest.domain.port.out.http.PostHandlePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InterestContentsExistHandleAdapter implements InterestContentsExistHandlePort {

  private final PostHandlePort postHandlePort;
  private final CommentHandlePort commentHandlePort;

  @Override
  public boolean isExistContents(String contentsType, Long contentsId) {
    switch (contentsType) {
      case ("POST") -> {
        return postHandlePort.isExistPost(contentsId);
      }
      case ("COMMENT") -> {
        return commentHandlePort.isExistComment(contentsId);
      }
      default -> {
        throw new IllegalArgumentException("적절한 컨텐츠 타입이 아닙니다.");
      }
    }
  }
}
