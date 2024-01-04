package com.pellto.youtoy.domain.video.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.domain.video.repository.VideoReplyCommentRepository;
import com.pellto.youtoy.domain.video.util.VideoCommentFactory;
import com.pellto.youtoy.domain.video.util.VideoReplyFactory;
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
class VideoReplyWriteServiceTest {

  private static final String TEST_NAME = "videoReplyWriteService";
  @InjectMocks
  private VideoReplyWriteService videoReplyWriteService;

  @Mock
  private VideoReplyCommentRepository videoReplyCommentRepository;
  @Mock
  private VideoReplyReadService videoReplyReadService;
  @Mock
  private VideoCommentReadService contentReadService;

  @DisplayName("[" + TEST_NAME + ": save: success] 답글 저장 테스트")
  @Test
  void writeSuccessTest() {
    var parentComment = VideoCommentFactory.create();
    var req = VideoReplyFactory.createWriteRequest(parentComment.getId());
    var reply = VideoReplyFactory.create(req);
    var replyDto = VideoReplyFactory.createDto(reply);

    given(contentReadService.getById(any())).willReturn(parentComment);
    given(videoReplyCommentRepository.save(any())).willReturn(reply);
    given(videoReplyReadService.toDto(reply)).willReturn(replyDto);

    var writtenReply = videoReplyWriteService.write(req);

    then(contentReadService).should(times(1)).getById(any());
    then(videoReplyCommentRepository).should(times(1)).save(any());
    then(videoReplyReadService).should(times(1)).toDto(reply);
    Assertions.assertThat(writtenReply).isEqualTo(replyDto);
  }
}