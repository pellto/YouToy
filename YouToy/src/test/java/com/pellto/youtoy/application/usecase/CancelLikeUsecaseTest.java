package com.pellto.youtoy.application.usecase;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.times;

import com.pellto.youtoy.domain.comment.service.CommentWriteService;
import com.pellto.youtoy.domain.like.service.DislikeWriteService;
import com.pellto.youtoy.domain.like.service.LikeReadService;
import com.pellto.youtoy.domain.like.service.LikeWriteService;
import com.pellto.youtoy.domain.view.service.ShortWriteService;
import com.pellto.youtoy.domain.view.service.VideoWriteService;
import com.pellto.youtoy.util.like.LikeFixtureFactory;
import com.pellto.youtoy.util.types.VideoTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@Tag("usecase")
@ExtendWith(MockitoExtension.class)
@DisplayName("[CancelLikeUsecase Test]")
public class CancelLikeUsecaseTest {

  private static final Long ID = 1L;
  @InjectMocks
  private CancelLikeUsecase cancelLikeUsecase;
  @Mock
  private LikeWriteService likeWriteService;
  @Mock
  private DislikeWriteService dislikeWriteService;
  @Mock
  private LikeReadService likeReadService;
  @Mock
  private CommentWriteService commentWriteService;
  @Mock
  private VideoWriteService videoWriteService;
  @Mock
  private ShortWriteService shortWriteService;

  @Nested
  @DisplayName("Cancel ExecuteDislike Test")
  public class ExecuteDislikeTest {

    @DisplayName("[dislike: success] 싫어요 취소 성공 테스트")
    @Test
    public void executeDislikeTest() {
      cancelLikeUsecase.executeDislike(ID);

      then(dislikeWriteService).should(times(1)).cancel(ID);
    }
  }

  @Nested
  @DisplayName("Cancel ExecuteLike Test")
  public class ExecuteLikeTest {

    @DisplayName("[commentLike: success] 댓글 좋아요 삭제 성공 테스트")
    @Test
    public void executeCommentLikeTest() {
      var likeDto = LikeFixtureFactory.toDto(
          LikeFixtureFactory.create(VideoTypes.COMMENT_TYPE.getValue())
      );

      given(likeReadService.getById(any())).willReturn(likeDto);

      cancelLikeUsecase.executeLike(ID);

      then(likeWriteService).should(times(1)).cancel(likeDto);
      then(commentWriteService).should(times(1)).decreaseLikeCount(any());
      then(videoWriteService).should(times(0)).decreaseLikeCount(any());
      then(shortWriteService).should(times(0)).decreaseLikeCount(any());
    }

    @DisplayName("[shortLike: success] 쇼츠 좋아요 삭제 성공 테스트")
    @Test
    public void executeShortLikeTest() {
      var likeDto = LikeFixtureFactory.toDto(
          LikeFixtureFactory.create(VideoTypes.SHORTS_TYPE.getValue())
      );

      given(likeReadService.getById(any())).willReturn(likeDto);

      cancelLikeUsecase.executeLike(ID);

      then(likeWriteService).should(times(1)).cancel(likeDto);
      then(commentWriteService).should(times(0)).decreaseLikeCount(any());
      then(videoWriteService).should(times(0)).decreaseLikeCount(any());
      then(shortWriteService).should(times(1)).decreaseLikeCount(any());
    }

    @DisplayName("[videoLike: success] 비디오 좋아요 삭제 성공 테스트")
    @Test
    public void executeVideoLikeTest() {
      var likeDto = LikeFixtureFactory.toDto(
          LikeFixtureFactory.create(VideoTypes.VIDEO_TYPE.getValue())
      );

      given(likeReadService.getById(any())).willReturn(likeDto);

      cancelLikeUsecase.executeLike(ID);

      then(likeWriteService).should(times(1)).cancel(likeDto);
      then(commentWriteService).should(times(0)).decreaseLikeCount(any());
      then(videoWriteService).should(times(1)).decreaseLikeCount(any());
      then(shortWriteService).should(times(0)).decreaseLikeCount(any());
    }
  }
}
