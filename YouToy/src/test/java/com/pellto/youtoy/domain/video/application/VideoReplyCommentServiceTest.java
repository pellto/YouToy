package com.pellto.youtoy.domain.video.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.domain.video.domain.VideoReplyComment;
import com.pellto.youtoy.domain.video.dto.VideoReplyCommentDto;
import com.pellto.youtoy.domain.video.repository.VideoReplyCommentRepository;
import com.pellto.youtoy.domain.video.util.VideoCommentFactory;
import com.pellto.youtoy.domain.video.util.VideoReplyFactory;
import java.util.ArrayList;
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
class VideoReplyCommentServiceTest {

  private static final String TEST_NAME = "videoReplyCommentService";
  @InjectMocks
  private VideoReplyCommentService videoReplyCommentService;

  @Mock
  private VideoReplyCommentRepository videoReplyCommentRepository;
  @Mock
  private VideoCommentReadService contentReadService;

  @DisplayName("[" + TEST_NAME + ": findAllByParentId: success] 부모 댓글의 답글 전체 조회 테스트")
  @Test
  void findAllSuccessTest() {
    var reply = VideoReplyFactory.create();
    var replyList = new ArrayList<VideoReplyComment>();
    replyList.add(reply);

    given(videoReplyCommentRepository.findAllByParentComment(any())).willReturn(replyList);
    given(contentReadService.getById(any())).willReturn(reply.getParentComment());

    var foundReplyList = videoReplyCommentService.findAllByParentId(
        reply.getParentComment().getId());

    then(videoReplyCommentRepository).should(times(1)).findAllByParentComment(any());
    then(contentReadService).should(times(1)).getById(any());
    Assertions.assertThat(foundReplyList).isNotNull();
    Assertions.assertThat(foundReplyList).isNotEmpty();
    Assertions.assertThat(foundReplyList.size()).isEqualTo(replyList.size());
    Assertions.assertThat(foundReplyList.get(0).getClass()).isEqualTo(VideoReplyCommentDto.class);
  }

  @DisplayName("[" + TEST_NAME + ": save: success] 답글 저장 테스트")
  @Test
  void writeSuccessTest() {
    var parentComment = VideoCommentFactory.create();
    var req = VideoReplyFactory.createWriteRequest(parentComment.getId());
    var reply = VideoReplyFactory.create(req);
    var replyDto = VideoReplyFactory.createDto(reply);

    given(contentReadService.getById(any())).willReturn(parentComment);
    given(videoReplyCommentRepository.save(any())).willReturn(reply);

    var writtenReply = videoReplyCommentService.write(req);

    then(contentReadService).should(times(1)).getById(any());
    then(videoReplyCommentRepository).should(times(1)).save(any());
    Assertions.assertThat(writtenReply).isEqualTo(replyDto);
  }
}