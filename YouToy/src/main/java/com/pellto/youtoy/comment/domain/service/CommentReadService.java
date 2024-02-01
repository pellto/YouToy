package com.pellto.youtoy.comment.domain.service;

import com.pellto.youtoy.comment.domain.model.Comment;
import com.pellto.youtoy.comment.domain.port.in.ReadCommentUsecase;
import com.pellto.youtoy.comment.domain.port.out.http.ContentsExistHandlePort;
import com.pellto.youtoy.comment.domain.port.out.persistence.LoadCommentPort;
import com.pellto.youtoy.global.dto.comment.CommentDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CommentReadService implements ReadCommentUsecase {

  private final LoadCommentPort loadCommentPort;
  private final ContentsExistHandlePort contentsExistHandlePort;

  @Override
  public List<CommentDto> readAllByContentsTypeAndContentsId(String contentsType, Long contentsId) {
    if (!contentsExistHandlePort.isExistContents(contentsType, contentsId)) {
      throw new IllegalArgumentException("해당 컨텐츠가 존재하지 않습니다.");
    }
    var comments = loadCommentPort.loadAllByContentsTypeAndContentsId(contentsType, contentsId);
    return comments.stream().map(Comment::toDto).toList();
  }
}
