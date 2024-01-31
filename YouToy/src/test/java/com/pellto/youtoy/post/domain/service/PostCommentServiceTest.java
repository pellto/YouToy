package com.pellto.youtoy.post.domain.service;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.comment.domain.model.CommenterInfo;
import com.pellto.youtoy.global.dto.comment.CommentDto;
import com.pellto.youtoy.post.domain.port.out.LoadPostPort;
import com.pellto.youtoy.post.domain.port.out.SavePostPort;
import com.pellto.youtoy.post.util.PostFixtureFactory;
import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@Tag("service")
class PostCommentServiceTest {

  private static final String SERVICE_NAME = "PostCommentService";
  @InjectMocks
  private PostCommentService postCommentService;

  @Mock
  private LoadPostPort loadPostPort;
  @Mock
  private SavePostPort savePostPort;

  @DisplayName("[" + SERVICE_NAME + "/increaseCommentCount] increaseCommentCount 성공 테스트")
  @Test
  void increaseCommentCountSuccessTest() {
    var post = PostFixtureFactory.create();
    // TODO: change comment Fixture Factory
    var commentDto = CommentDto.builder()
        .id(1L)
        .commenterInfo(
            CommenterInfo.builder()
                .commenterId(1L)
                .commenterHandle("test-handle")
                .displayName("displayName")
                .profilePath("profilePath")
                .build()
        ).contentsId(1L)
        .commentContentsType("POST")
        .content("content")
        .ownerLike(false)
        .replyCount(0L)
        .likeCount(0L)
        .createdAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now())
        .build();
    var publisher = "PostCommentServiceTest/increaseCommentCount";
    var event = PostFixtureFactory.createCommentWrittenEvent(commentDto, publisher);

    given(loadPostPort.load(event.getDto().contentsId())).willReturn(post);

    postCommentService.increaseCommentCount(event);

    then(loadPostPort).should(times(1)).load(any());
    then(savePostPort).should(times(1)).update(any());
    Assertions.assertThat(post.getCommentCount()).isEqualTo(1L);
  }

}