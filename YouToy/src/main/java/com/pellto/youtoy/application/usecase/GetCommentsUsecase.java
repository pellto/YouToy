package com.pellto.youtoy.application.usecase;

import com.pellto.youtoy.domain.comment.dto.CommentDto;
import com.pellto.youtoy.domain.comment.service.CommentReadService;
import com.pellto.youtoy.domain.view.service.ShortReadService;
import com.pellto.youtoy.domain.view.service.VideoReadService;
import com.pellto.youtoy.util.error.ErrorCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GetCommentsUsecase {

  private final VideoReadService videoReadService;
  private final ShortReadService shortReadService;
  private final CommentReadService commentReadService;

  // TODO: Comment to dto
  public List<CommentDto> execute(Long videoId, Boolean isVideo) {
    if (isVideo) {
      if (!videoReadService.existVideo(videoId)) {
        // TODO: Convert to Custom Error
        throw new UnsupportedOperationException(ErrorCode.NOT_EXIST_VIDEO.getMessage());
      }
    } else {
      if (!shortReadService.existShort(videoId)) {
        // TODO: Convert to Custom Error
        throw new UnsupportedOperationException(ErrorCode.NOT_EXIST_SHORT.getMessage());
      }
    }

    return commentReadService.getByVideoIdAndVideo(videoId, isVideo);
  }
}
