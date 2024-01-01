package com.pellto.youtoy.domain.video.application;

import com.pellto.youtoy.domain.base.application.WriteService;
import com.pellto.youtoy.domain.base.dto.ModifyCommentRequest;
import com.pellto.youtoy.domain.base.dto.WriteCommentRequest;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import com.pellto.youtoy.domain.video.domain.VideoComment;
import com.pellto.youtoy.domain.video.dto.VideoCommentDto;
import com.pellto.youtoy.domain.video.repository.VideoCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VideoCommentWriteService extends
    WriteService<VideoCommentDto, WriteCommentRequest, ModifyCommentRequest> {

  private final VideoCommentRepository videoCommentRepository;
  private final VideoCommentReadService videoCommentReadService;
  private final VideoReadService videoReadService;

  @Override
  public VideoCommentDto write(WriteCommentRequest req) {
    var video = videoReadService.getById(req.contentId());
    var commenter = new UserUUID(req.commenterUuid());
    var comment = VideoComment.builder()
        .contents(video)
        .commentContent(req.content())
        .commenterUuid(commenter)
        .build();
    return videoCommentReadService.toDto(videoCommentRepository.save(comment));
  }

  @Override
  public VideoCommentDto modify(ModifyCommentRequest modifyRequest) {
    return null;
  }

  @Override
  public void deleteById(Long id) {

  }
}
