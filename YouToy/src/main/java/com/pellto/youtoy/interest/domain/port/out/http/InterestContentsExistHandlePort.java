package com.pellto.youtoy.interest.domain.port.out.http;

public interface InterestContentsExistHandlePort {

  boolean isExistContents(String contentsType, Long contentsId);
}
