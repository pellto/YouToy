package com.pellto.youtoy.comment.domain.port.out.http;

public interface ContentsExistHandlePort {

  boolean isExistContents(String contentsType, Long contentsId);
}
