package com.pellto.youtoy.comment.domain.port.in;

import com.pellto.youtoy.global.dto.comment.CommentDto;
import java.util.List;

public interface ReadCommentUsecase {

  List<CommentDto> readAllByContentsTypeAndContentsId(String contentsType, Long contentsId);
}
