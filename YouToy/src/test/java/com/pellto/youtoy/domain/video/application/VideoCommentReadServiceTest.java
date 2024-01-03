package com.pellto.youtoy.domain.video.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.domain.video.domain.VideoComment;
import com.pellto.youtoy.domain.video.dto.VideoCommentDto;
import com.pellto.youtoy.domain.video.repository.VideoCommentRepository;
import com.pellto.youtoy.domain.video.util.VideoCommentFactory;
import java.util.ArrayList;
import java.util.Optional;
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
class VideoCommentReadServiceTest {

  private static final String SERVICE_NAME = "VideoCommentReadService";

  @InjectMocks
  private VideoCommentReadService videoCommentReadService;

  @Mock
  private VideoCommentRepository videoCommentRepository;

  @DisplayName("[" + SERVICE_NAME + ": findAll: success] 전체 조회 성공 테스트")
  @Test
  void findAllTest() {
    var comment = VideoCommentFactory.create();
    var commentList = new ArrayList<VideoComment>();
    commentList.add(comment);

    given(videoCommentRepository.findAll()).willReturn(commentList);

    var foundCommentList = videoCommentReadService.findAll();

    then(videoCommentRepository).should(times(1)).findAll();
    Assertions.assertThat(foundCommentList).isNotEmpty();
    Assertions.assertThat(foundCommentList.size()).isEqualTo(1);
    Assertions.assertThat(foundCommentList.get(0).getClass()).isEqualTo(VideoCommentDto.class);
  }

  @DisplayName("[" + SERVICE_NAME + ": findById: success] id 조건 조회 성공 테스트")
  @Test
  void findByIdSuccessTest() {
    var comment = VideoCommentFactory.create();

    given(videoCommentRepository.findById(any())).willReturn(Optional.ofNullable(comment));

    var foundComment = videoCommentReadService.findById(comment.getId());

    then(videoCommentRepository).should(times(1)).findById(comment.getId());
    Assertions.assertThat(foundComment).isNotNull();
    Assertions.assertThat(foundComment.id()).isEqualTo(comment.getId());
    Assertions.assertThat(foundComment.getClass()).isEqualTo(VideoCommentDto.class);
  }
}