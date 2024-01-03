package com.pellto.youtoy.domain.video.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.domain.video.dto.VideoCommentDto;
import com.pellto.youtoy.domain.video.repository.VideoCommentRepository;
import com.pellto.youtoy.domain.video.util.VideoCommentFactory;
import com.pellto.youtoy.domain.video.util.VideoFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@Tag("service")
@ExtendWith(MockitoExtension.class)
class VideoCommentWriteServiceTest {

  private static final String SERVICE_NAME = "VideoCommentWriteService";
  @InjectMocks
  private VideoCommentWriteService videoCommentWriteService;

  @Mock
  private VideoCommentRepository videoCommentRepository;
  @Mock
  private VideoCommentReadService videoCommentReadService;
  @Mock
  private VideoReadService videoReadService;


  @DisplayName("[" + SERVICE_NAME + ": write: success] 댓글 저장 성공 테스트")
  @Test
  void writeSuccessTest() {
    var video = VideoFactory.create();
    var req = VideoCommentFactory.createWriteCommentRequest(video.getId());
    var comment = VideoCommentFactory.create(req);
    var commentDto = VideoCommentFactory.createCommentDto(comment);

    given(videoReadService.getById(video.getId())).willReturn(video);
    given(videoCommentRepository.save(any())).willReturn(comment);
    given(videoCommentReadService.toDto(comment)).willReturn(commentDto);

    var writtenComment = videoCommentWriteService.write(req);

    then(videoReadService).should(times(1)).getById(any());
    then(videoCommentRepository).should(times(1)).save(any());
    then(videoCommentReadService).should(times(1)).toDto(comment);
    Assertions.assertThat(writtenComment).isNotNull();
    Assertions.assertThat(writtenComment.getClass()).isEqualTo(VideoCommentDto.class);
    Assertions.assertThat(writtenComment.commentContent()).isEqualTo(req.content());
    Assertions.assertThat(writtenComment.commenterUuid()).isEqualTo(req.commenterUuid());
    Assertions.assertThat(writtenComment.contentsId()).isEqualTo(req.contentId());
  }

}