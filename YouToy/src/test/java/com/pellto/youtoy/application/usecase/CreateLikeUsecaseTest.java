package com.pellto.youtoy.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.times;

import com.pellto.youtoy.domain.comment.service.CommentReadService;
import com.pellto.youtoy.domain.comment.service.CommentWriteService;
import com.pellto.youtoy.domain.like.service.DislikeReadService;
import com.pellto.youtoy.domain.like.service.DislikeWriteService;
import com.pellto.youtoy.domain.like.service.LikeReadService;
import com.pellto.youtoy.domain.like.service.LikeWriteService;
import com.pellto.youtoy.domain.view.service.ShortReadService;
import com.pellto.youtoy.domain.view.service.ShortWriteService;
import com.pellto.youtoy.domain.view.service.VideoReadService;
import com.pellto.youtoy.domain.view.service.VideoWriteService;
import com.pellto.youtoy.util.error.ErrorCode;
import com.pellto.youtoy.util.like.CreateLikeCommandFixtureFactory;
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
@DisplayName("[CreateLikeUsecase Test]")
public class CreateLikeUsecaseTest {

  @InjectMocks
  private CreateLikeUsecase createLikeUsecase;
  @Mock
  private LikeWriteService likeWriteService;
  @Mock
  private LikeReadService likeReadService;
  @Mock
  private DislikeWriteService dislikeWriteService;
  @Mock
  private DislikeReadService dislikeReadService;
  @Mock
  private VideoReadService videoReadService;
  @Mock
  private VideoWriteService videoWriteService;
  @Mock
  private ShortWriteService shortWriteService;
  @Mock
  private ShortReadService shortReadService;
  @Mock
  private CommentReadService commentReadService;
  @Mock
  private CommentWriteService commentWriteService;

  @Nested
  @DisplayName("executeDislike(CreateLikeCommand cmd)")
  public class ExecuteDislikeTest {

    @DisplayName("[comment: dislike decrease success without like] 댓글 싫어요 생성 성공 테스트")
    @Test
    public void executeCommentDislikeTest() {
      var videoType = VideoTypes.COMMENT_TYPE.getValue();
      var cmd = CreateLikeCommandFixtureFactory.create(videoType);

      given(commentReadService.existComment(cmd.commentId())).willReturn(true);
      given(likeReadService.getByCreateCmd(cmd)).willReturn(null);

      createLikeUsecase.executeDislike(cmd);

      // check(cmd);
      then(commentReadService).should(times(1)).existComment(cmd.commentId());
      then(videoReadService).should(times(0)).existVideo(any());
      then(shortReadService).should(times(0)).existShort(any());
      // crossCheck(cmd, true);
      then(dislikeReadService).should(times(0)).getByCreateCmd(any());
      then(dislikeWriteService).should(times(0)).cancel(any());
      then(likeReadService).should(times(1)).getByCreateCmd(cmd);
      then(likeWriteService).should(times(0)).cancel((Long) any());
      // dislikeWriteService.dislike(cmd);
      then(likeWriteService).should(times(0)).like(any());
      then(dislikeWriteService).should(times(1)).dislike(cmd);
      // fluctuateLikeCount(cmd, isIncrease);
      then(commentWriteService).should(times(0)).increaseLikeCount(any());
      then(commentWriteService).should(times(0)).decreaseLikeCount(any());
      then(videoWriteService).should(times(0)).increaseLikeCount(any());
      then(videoWriteService).should(times(0)).decreaseLikeCount(any());
      then(shortWriteService).should(times(0)).increaseLikeCount(any());
      then(shortWriteService).should(times(0)).decreaseLikeCount(any());
    }

    @DisplayName("[comment: dislike decrease success with like] 댓글 싫어요시 좋아요 삭제 성공 테스트")
    @Test
    public void executeCommentDislikeWithLikeTest() {
      var videoType = VideoTypes.COMMENT_TYPE.getValue();
      var cmd = CreateLikeCommandFixtureFactory.create(videoType);
      var likeDto = LikeFixtureFactory.toDto(LikeFixtureFactory.create(videoType));

      given(commentReadService.existComment(cmd.commentId())).willReturn(true);
      given(likeReadService.getByCreateCmd(cmd)).willReturn(likeDto);

      createLikeUsecase.executeDislike(cmd);

      // check(cmd);
      then(commentReadService).should(times(1)).existComment(cmd.commentId());
      then(videoReadService).should(times(0)).existVideo(any());
      then(shortReadService).should(times(0)).existShort(any());
      // crossCheck(cmd, true);
      then(dislikeReadService).should(times(0)).getByCreateCmd(any());
      then(dislikeWriteService).should(times(0)).cancel(any());
      then(likeReadService).should(times(1)).getByCreateCmd(cmd);
      then(likeWriteService).should(times(1)).cancel((Long) any());
      // dislikeWriteService.dislike(cmd);
      then(likeWriteService).should(times(0)).like(any());
      then(dislikeWriteService).should(times(1)).dislike(cmd);
      // fluctuateLikeCount(cmd, isIncrease);
      then(commentWriteService).should(times(0)).increaseLikeCount(any());
      then(commentWriteService).should(times(1)).decreaseLikeCount(cmd.commentId());
      then(videoWriteService).should(times(0)).increaseLikeCount(any());
      then(videoWriteService).should(times(0)).decreaseLikeCount(any());
      then(shortWriteService).should(times(0)).increaseLikeCount(any());
      then(shortWriteService).should(times(0)).decreaseLikeCount(any());
    }

    @DisplayName("[comment: not exist comment] 존재하지 않는 댓글 싫어요 실패 테스트")
    @Test
    public void executeNotExistCommentTest() {
      var videoType = VideoTypes.COMMENT_TYPE.getValue();
      var cmd = CreateLikeCommandFixtureFactory.create(videoType);

      given(commentReadService.existComment(cmd.commentId())).willReturn(false);

      try {
        createLikeUsecase.executeDislike(cmd);
      } catch (Exception e) {
        assertEquals(ErrorCode.NOT_EXIST_COMMENT.getMessage(), e.getMessage());
        // check(cmd);
        then(commentReadService).should(times(1)).existComment(cmd.commentId());
        then(videoReadService).should(times(0)).existVideo(any());
        then(shortReadService).should(times(0)).existShort(any());
        // crossCheck(cmd, true);
        then(dislikeReadService).should(times(0)).getByCreateCmd(any());
        then(dislikeWriteService).should(times(0)).cancel(any());
        then(likeReadService).should(times(0)).getByCreateCmd(cmd);
        then(likeWriteService).should(times(0)).cancel((Long) any());
        // dislikeWriteService.dislike(cmd);
        then(likeWriteService).should(times(0)).like(any());
        then(dislikeWriteService).should(times(0)).dislike(cmd);
        // fluctuateLikeCount(cmd, isIncrease);
        then(commentWriteService).should(times(0)).increaseLikeCount(any());
        then(commentWriteService).should(times(0)).decreaseLikeCount(any());
        then(videoWriteService).should(times(0)).increaseLikeCount(any());
        then(videoWriteService).should(times(0)).decreaseLikeCount(any());
        then(shortWriteService).should(times(0)).increaseLikeCount(any());
        then(shortWriteService).should(times(0)).decreaseLikeCount(any());
      }
    }

    @DisplayName("[short: not exist comment] 존재하지 않는 쇼츠 싫어요 실패 테스트")
    @Test
    public void executeNotExistShortTest() {
      var videoType = VideoTypes.SHORTS_TYPE.getValue();
      var cmd = CreateLikeCommandFixtureFactory.create(videoType);

      given(shortReadService.existShort(cmd.videoId())).willReturn(false);

      try {
        createLikeUsecase.executeDislike(cmd);
      } catch (Exception e) {
        assertEquals(ErrorCode.NOT_EXIST_SHORT.getMessage(), e.getMessage());
        // check(cmd);
        then(commentReadService).should(times(0)).existComment(any());
        then(videoReadService).should(times(0)).existVideo(any());
        then(shortReadService).should(times(1)).existShort(cmd.videoId());
        // crossCheck(cmd, true);
        then(dislikeReadService).should(times(0)).getByCreateCmd(any());
        then(dislikeWriteService).should(times(0)).cancel(any());
        then(likeReadService).should(times(0)).getByCreateCmd(cmd);
        then(likeWriteService).should(times(0)).cancel((Long) any());
        // dislikeWriteService.dislike(cmd);
        then(likeWriteService).should(times(0)).like(any());
        then(dislikeWriteService).should(times(0)).dislike(cmd);
        // fluctuateLikeCount(cmd, isIncrease);
        then(commentWriteService).should(times(0)).increaseLikeCount(any());
        then(commentWriteService).should(times(0)).decreaseLikeCount(any());
        then(videoWriteService).should(times(0)).increaseLikeCount(any());
        then(videoWriteService).should(times(0)).decreaseLikeCount(any());
        then(shortWriteService).should(times(0)).increaseLikeCount(any());
        then(shortWriteService).should(times(0)).decreaseLikeCount(any());
      }
    }

    @DisplayName("[video: not exist comment] 존재하지 않는 비디오 싫어요 실패 테스트")
    @Test
    public void executeNotExistVideoTest() {
      var videoType = VideoTypes.VIDEO_TYPE.getValue();
      var cmd = CreateLikeCommandFixtureFactory.create(videoType);

      given(videoReadService.existVideo(cmd.videoId())).willReturn(false);

      try {
        createLikeUsecase.executeDislike(cmd);
      } catch (Exception e) {
        assertEquals(ErrorCode.NOT_EXIST_VIDEO.getMessage(), e.getMessage());
        // check(cmd);
        then(commentReadService).should(times(0)).existComment(any());
        then(videoReadService).should(times(1)).existVideo(cmd.videoId());
        then(shortReadService).should(times(0)).existShort(any());
        // crossCheck(cmd, true);
        then(dislikeReadService).should(times(0)).getByCreateCmd(any());
        then(dislikeWriteService).should(times(0)).cancel(any());
        then(likeReadService).should(times(0)).getByCreateCmd(cmd);
        then(likeWriteService).should(times(0)).cancel((Long) any());
        // dislikeWriteService.dislike(cmd);
        then(likeWriteService).should(times(0)).like(any());
        then(dislikeWriteService).should(times(0)).dislike(cmd);
        // fluctuateLikeCount(cmd, isIncrease);
        then(commentWriteService).should(times(0)).increaseLikeCount(any());
        then(commentWriteService).should(times(0)).decreaseLikeCount(any());
        then(videoWriteService).should(times(0)).increaseLikeCount(any());
        then(videoWriteService).should(times(0)).decreaseLikeCount(any());
        then(shortWriteService).should(times(0)).increaseLikeCount(any());
        then(shortWriteService).should(times(0)).decreaseLikeCount(any());
      }
    }

    @DisplayName("[short: dislike decrease success without like] 쇼츠 싫어요 생성 성공 테스트")
    @Test
    public void executeShortDislikeTest() {
      var videoType = VideoTypes.SHORTS_TYPE.getValue();
      var cmd = CreateLikeCommandFixtureFactory.create(videoType);

      given(shortReadService.existShort(cmd.videoId())).willReturn(true);
      given(likeReadService.getByCreateCmd(cmd)).willReturn(null);

      createLikeUsecase.executeDislike(cmd);

      // check(cmd);
      then(commentReadService).should(times(0)).existComment(any());
      then(videoReadService).should(times(0)).existVideo(any());
      then(shortReadService).should(times(1)).existShort(cmd.videoId());
      // crossCheck(cmd, true);
      then(dislikeReadService).should(times(0)).getByCreateCmd(any());
      then(dislikeWriteService).should(times(0)).cancel(any());
      then(likeReadService).should(times(1)).getByCreateCmd(cmd);
      then(likeWriteService).should(times(0)).cancel((Long) any());
      // dislikeWriteService.dislike(cmd);
      then(likeWriteService).should(times(0)).like(any());
      then(dislikeWriteService).should(times(1)).dislike(cmd);
      // fluctuateLikeCount(cmd, isIncrease);
      then(commentWriteService).should(times(0)).increaseLikeCount(any());
      then(commentWriteService).should(times(0)).decreaseLikeCount(any());
      then(videoWriteService).should(times(0)).increaseLikeCount(any());
      then(videoWriteService).should(times(0)).decreaseLikeCount(any());
      then(shortWriteService).should(times(0)).increaseLikeCount(any());
      then(shortWriteService).should(times(0)).decreaseLikeCount(any());
    }

    @DisplayName("[short: dislike decrease success with like] 쇼츠 싫어요시 좋아요 삭제 성공 테스트")
    @Test
    public void executeShortDislikeWithLikeTest() {
      var videoType = VideoTypes.SHORTS_TYPE.getValue();
      var cmd = CreateLikeCommandFixtureFactory.create(videoType);
      var likeDto = LikeFixtureFactory.toDto(LikeFixtureFactory.create(videoType));

      given(shortReadService.existShort(cmd.videoId())).willReturn(true);
      given(likeReadService.getByCreateCmd(cmd)).willReturn(likeDto);

      createLikeUsecase.executeDislike(cmd);

      // check(cmd);
      then(commentReadService).should(times(0)).existComment(any());
      then(videoReadService).should(times(0)).existVideo(any());
      then(shortReadService).should(times(1)).existShort(cmd.videoId());
      // crossCheck(cmd, true);
      then(dislikeReadService).should(times(0)).getByCreateCmd(any());
      then(dislikeWriteService).should(times(0)).cancel(any());
      then(likeReadService).should(times(1)).getByCreateCmd(cmd);
      then(likeWriteService).should(times(1)).cancel((Long) any());
      // dislikeWriteService.dislike(cmd);
      then(likeWriteService).should(times(0)).like(any());
      then(dislikeWriteService).should(times(1)).dislike(cmd);
      // fluctuateLikeCount(cmd, isIncrease);
      then(commentWriteService).should(times(0)).increaseLikeCount(any());
      then(commentWriteService).should(times(0)).decreaseLikeCount(any());
      then(videoWriteService).should(times(0)).increaseLikeCount(any());
      then(videoWriteService).should(times(0)).decreaseLikeCount(any());
      then(shortWriteService).should(times(0)).increaseLikeCount(any());
      then(shortWriteService).should(times(1)).decreaseLikeCount(cmd.videoId());
    }

    @DisplayName("[comment: unsupported like case] 지원하지 않는 like case 실패 테스트")
    @Test
    public void executeUnsupportedLikeCaseTest() {
      var videoType = VideoTypes.COMMENT_TYPE.getValue();
      var cmd = CreateLikeCommandFixtureFactory.create(videoType, null);

      try {
        createLikeUsecase.executeDislike(cmd);
      } catch (Exception e) {
        assertEquals(ErrorCode.UNSUPPORTED_LIKE_CASE.getMessage(), e.getMessage());
        // check(cmd);
        then(commentReadService).should(times(0)).existComment(any());
        then(videoReadService).should(times(0)).existVideo(any());
        then(shortReadService).should(times(0)).existShort(any());
        // crossCheck(cmd, true);
        then(dislikeReadService).should(times(0)).getByCreateCmd(any());
        then(dislikeWriteService).should(times(0)).cancel(any());
        then(likeReadService).should(times(0)).getByCreateCmd(cmd);
        then(likeWriteService).should(times(0)).cancel((Long) any());
        // dislikeWriteService.dislike(cmd);
        then(likeWriteService).should(times(0)).like(any());
        then(dislikeWriteService).should(times(0)).dislike(cmd);
        // fluctuateLikeCount(cmd, isIncrease);
        then(commentWriteService).should(times(0)).increaseLikeCount(any());
        then(commentWriteService).should(times(0)).decreaseLikeCount(any());
        then(videoWriteService).should(times(0)).increaseLikeCount(any());
        then(videoWriteService).should(times(0)).decreaseLikeCount(any());
        then(shortWriteService).should(times(0)).increaseLikeCount(any());
        then(shortWriteService).should(times(0)).decreaseLikeCount(any());
      }
    }

    @DisplayName("[video: dislike decrease success without like] 비디오 싫어요 생성 성공 테스트")
    @Test
    public void executeVideoDislikeTest() {
      var videoType = VideoTypes.VIDEO_TYPE.getValue();
      var cmd = CreateLikeCommandFixtureFactory.create(videoType);

      given(videoReadService.existVideo(cmd.videoId())).willReturn(true);
      given(likeReadService.getByCreateCmd(cmd)).willReturn(null);

      createLikeUsecase.executeDislike(cmd);

      // check(cmd);
      then(commentReadService).should(times(0)).existComment(any());
      then(videoReadService).should(times(1)).existVideo(cmd.videoId());
      then(shortReadService).should(times(0)).existShort(any());
      // crossCheck(cmd, true);
      then(dislikeReadService).should(times(0)).getByCreateCmd(any());
      then(dislikeWriteService).should(times(0)).cancel(any());
      then(likeReadService).should(times(1)).getByCreateCmd(cmd);
      then(likeWriteService).should(times(0)).cancel((Long) any());
      // dislikeWriteService.dislike(cmd);
      then(likeWriteService).should(times(0)).like(any());
      then(dislikeWriteService).should(times(1)).dislike(cmd);
      // fluctuateLikeCount(cmd, isIncrease);
      then(commentWriteService).should(times(0)).increaseLikeCount(any());
      then(commentWriteService).should(times(0)).decreaseLikeCount(any());
      then(videoWriteService).should(times(0)).increaseLikeCount(any());
      then(videoWriteService).should(times(0)).decreaseLikeCount(any());
      then(shortWriteService).should(times(0)).increaseLikeCount(any());
      then(shortWriteService).should(times(0)).decreaseLikeCount(any());
    }

    @DisplayName("[video: dislike decrease success with like] 비디오 싫어요시 좋아요 삭제 성공 테스트")
    @Test
    public void executeVideoDislikeWithLikeTest() {
      var videoType = VideoTypes.VIDEO_TYPE.getValue();
      var cmd = CreateLikeCommandFixtureFactory.create(videoType);
      var likeDto = LikeFixtureFactory.toDto(LikeFixtureFactory.create(videoType));

      given(videoReadService.existVideo(cmd.videoId())).willReturn(true);
      given(likeReadService.getByCreateCmd(cmd)).willReturn(likeDto);

      createLikeUsecase.executeDislike(cmd);

      // check(cmd);
      then(commentReadService).should(times(0)).existComment(any());
      then(videoReadService).should(times(1)).existVideo(cmd.videoId());
      then(shortReadService).should(times(0)).existShort(any());
      // crossCheck(cmd, true);
      then(dislikeReadService).should(times(0)).getByCreateCmd(any());
      then(dislikeWriteService).should(times(0)).cancel(any());
      then(likeReadService).should(times(1)).getByCreateCmd(cmd);
      then(likeWriteService).should(times(1)).cancel((Long) any());
      // dislikeWriteService.dislike(cmd);
      then(likeWriteService).should(times(0)).like(any());
      then(dislikeWriteService).should(times(1)).dislike(cmd);
      // fluctuateLikeCount(cmd, isIncrease);
      then(commentWriteService).should(times(0)).increaseLikeCount(any());
      then(commentWriteService).should(times(0)).decreaseLikeCount(any());
      then(videoWriteService).should(times(0)).increaseLikeCount(any());
      then(videoWriteService).should(times(1)).decreaseLikeCount(cmd.videoId());
      then(shortWriteService).should(times(0)).increaseLikeCount(any());
      then(shortWriteService).should(times(0)).decreaseLikeCount(any());
    }
  }

  @Nested
  @DisplayName("executeLike(CreateLikeCommand cmd)")
  public class ExecuteLikeTest {

    @DisplayName("[comment: like increase success without dislike] 댓글 좋아요 생성 성공 테스트")
    @Test
    public void executeCommentLikeTest() {
      var videoType = VideoTypes.COMMENT_TYPE.getValue();
      var cmd = CreateLikeCommandFixtureFactory.create(videoType);

      given(commentReadService.existComment(any())).willReturn(true);
      given(dislikeReadService.getByCreateCmd(any())).willReturn(null);

      createLikeUsecase.executeLike(cmd);

      // check(cmd);
      then(commentReadService).should(times(1)).existComment(cmd.commentId());
      then(videoReadService).should(times(0)).existVideo(any());
      then(shortReadService).should(times(0)).existShort(any());
      // crossCheck(cmd, true);
      then(dislikeReadService).should(times(1)).getByCreateCmd(any());
      then(dislikeWriteService).should(times(0)).cancel(any());
      then(likeReadService).should(times(0)).getByCreateCmd(any());
      then(likeWriteService).should(times(0)).cancel((Long) any());
      // likeWriteService.like(cmd);
      then(likeWriteService).should(times(1)).like(cmd);
      then(dislikeWriteService).should(times(0)).dislike(any());
      // fluctuateLikeCount(cmd, isIncrease);
      then(commentWriteService).should(times(1)).increaseLikeCount(cmd.commentId());
      then(commentWriteService).should(times(0)).decreaseLikeCount(any());
      then(videoWriteService).should(times(0)).increaseLikeCount(any());
      then(videoWriteService).should(times(0)).decreaseLikeCount(any());
      then(shortWriteService).should(times(0)).increaseLikeCount(any());
      then(shortWriteService).should(times(0)).decreaseLikeCount(any());
    }

    @DisplayName("[comment: like increase success with dislike] 댓글 좋아요 생성시 싫어요 삭제 성공 테스트")
    @Test
    public void executeCommentLikeWithDislikeTest() {
      var videoType = VideoTypes.COMMENT_TYPE.getValue();
      var cmd = CreateLikeCommandFixtureFactory.create(videoType);
      var dislikeDto = LikeFixtureFactory.toDto(LikeFixtureFactory.create(videoType));

      given(commentReadService.existComment(any())).willReturn(true);
      given(dislikeReadService.getByCreateCmd(any())).willReturn(dislikeDto);

      createLikeUsecase.executeLike(cmd);

      // check(cmd);
      then(commentReadService).should(times(1)).existComment(cmd.commentId());
      then(videoReadService).should(times(0)).existVideo(any());
      then(shortReadService).should(times(0)).existShort(any());
      // crossCheck(cmd, true);
      then(dislikeReadService).should(times(1)).getByCreateCmd(any());
      then(dislikeWriteService).should(times(1)).cancel(dislikeDto.id());
      then(likeReadService).should(times(0)).getByCreateCmd(any());
      then(likeWriteService).should(times(0)).cancel((Long) any());
      // likeWriteService.like(cmd);
      then(likeWriteService).should(times(1)).like(cmd);
      then(dislikeWriteService).should(times(0)).dislike(any());
      // fluctuateLikeCount(cmd, isIncrease);
      then(commentWriteService).should(times(1)).increaseLikeCount(cmd.commentId());
      then(commentWriteService).should(times(0)).decreaseLikeCount(any());
      then(videoWriteService).should(times(0)).increaseLikeCount(any());
      then(videoWriteService).should(times(0)).decreaseLikeCount(any());
      then(shortWriteService).should(times(0)).increaseLikeCount(any());
      then(shortWriteService).should(times(0)).decreaseLikeCount(any());
    }

    @DisplayName("[comment: Not Exist Comment] 없는 댓글에 좋아요 실패 테스트")
    @Test
    public void executeNotExistCommentTest() {
      var videoType = VideoTypes.COMMENT_TYPE.getValue();
      var cmd = CreateLikeCommandFixtureFactory.create(videoType);

      given(commentReadService.existComment(any())).willReturn(false);

      try {
        createLikeUsecase.executeLike(cmd);
      } catch (Exception e) {
        assertEquals(ErrorCode.NOT_EXIST_COMMENT.getMessage(), e.getMessage());
        // check(cmd);
        then(commentReadService).should(times(1)).existComment(any());
        then(videoReadService).should(times(0)).existVideo(any());
        then(shortReadService).should(times(0)).existShort(any());
        // crossCheck(cmd, true);
        then(dislikeReadService).should(times(0)).getByCreateCmd(any());
        then(dislikeWriteService).should(times(0)).cancel(any());
        then(likeReadService).should(times(0)).getByCreateCmd(any());
        then(likeWriteService).should(times(0)).cancel((Long) any());
        // likeWriteService.like(cmd);
        then(likeWriteService).should(times(0)).like(cmd);
        then(dislikeWriteService).should(times(0)).dislike(any());
        // fluctuateLikeCount(cmd, isIncrease);
        then(commentWriteService).should(times(0)).increaseLikeCount(any());
        then(commentWriteService).should(times(0)).decreaseLikeCount(any());
        then(videoWriteService).should(times(0)).increaseLikeCount(any());
        then(videoWriteService).should(times(0)).decreaseLikeCount(any());
        then(shortWriteService).should(times(0)).increaseLikeCount(any());
        then(shortWriteService).should(times(0)).decreaseLikeCount(any());
      }
    }

    @DisplayName("[short: not exist short] 없는 쇼츠에 좋아요 실패 테스트")
    @Test
    public void executeNotExistShortTest() {
      var videoType = VideoTypes.SHORTS_TYPE.getValue();
      var cmd = CreateLikeCommandFixtureFactory.create(videoType);

      given(shortReadService.existShort(any())).willReturn(false);

      try {
        createLikeUsecase.executeLike(cmd);
      } catch (Exception e) {
        assertEquals(ErrorCode.NOT_EXIST_SHORT.getMessage(), e.getMessage());
        // check(cmd);
        then(commentReadService).should(times(0)).existComment(any());
        then(videoReadService).should(times(0)).existVideo(any());
        then(shortReadService).should(times(1)).existShort(cmd.videoId());
        // crossCheck(cmd, true);
        then(dislikeReadService).should(times(0)).getByCreateCmd(any());
        then(dislikeWriteService).should(times(0)).cancel(any());
        then(likeReadService).should(times(0)).getByCreateCmd(any());
        then(likeWriteService).should(times(0)).cancel((Long) any());
        // likeWriteService.like(cmd);
        then(likeWriteService).should(times(0)).like(any());
        then(dislikeWriteService).should(times(0)).dislike(any());
        // fluctuateLikeCount(cmd, isIncrease);
        then(commentWriteService).should(times(0)).increaseLikeCount(any());
        then(commentWriteService).should(times(0)).decreaseLikeCount(any());
        then(videoWriteService).should(times(0)).increaseLikeCount(any());
        then(videoWriteService).should(times(0)).decreaseLikeCount(any());
        then(shortWriteService).should(times(0)).increaseLikeCount(any());
        then(shortWriteService).should(times(0)).decreaseLikeCount(any());
      }
    }

    @DisplayName("[video: not exist video] 없는 비디오에 좋아요 실패 테스트")
    @Test
    public void executeNotExistVideoTest() {
      var videoType = VideoTypes.VIDEO_TYPE.getValue();
      var cmd = CreateLikeCommandFixtureFactory.create(videoType);

      given(videoReadService.existVideo(any())).willReturn(false);

      try {
        createLikeUsecase.executeLike(cmd);
      } catch (Exception e) {
        assertEquals(ErrorCode.NOT_EXIST_VIDEO.getMessage(), e.getMessage());
        // check(cmd);
        then(commentReadService).should(times(0)).existComment(any());
        then(videoReadService).should(times(1)).existVideo(cmd.videoId());
        then(shortReadService).should(times(0)).existShort(any());
        // crossCheck(cmd, true);
        then(dislikeReadService).should(times(0)).getByCreateCmd(any());
        then(dislikeWriteService).should(times(0)).cancel(any());
        then(likeReadService).should(times(0)).getByCreateCmd(any());
        then(likeWriteService).should(times(0)).cancel((Long) any());
        // likeWriteService.like(cmd);
        then(likeWriteService).should(times(0)).like(any());
        then(dislikeWriteService).should(times(0)).dislike(any());
        // fluctuateLikeCount(cmd, isIncrease);
        then(commentWriteService).should(times(0)).increaseLikeCount(any());
        then(commentWriteService).should(times(0)).decreaseLikeCount(any());
        then(videoWriteService).should(times(0)).increaseLikeCount(any());
        then(videoWriteService).should(times(0)).decreaseLikeCount(any());
        then(shortWriteService).should(times(0)).increaseLikeCount(any());
        then(shortWriteService).should(times(0)).decreaseLikeCount(any());
      }
    }

    @DisplayName("[short: like increase success without dislike] 쇼츠 좋아요 생성 성공 테스트")
    @Test
    public void executeShortLikeTest() {
      var videoType = VideoTypes.SHORTS_TYPE.getValue();
      var cmd = CreateLikeCommandFixtureFactory.create(videoType);

      given(shortReadService.existShort(any())).willReturn(true);
      given(dislikeReadService.getByCreateCmd(any())).willReturn(null);

      createLikeUsecase.executeLike(cmd);

      // check(cmd)
      then(commentReadService).should(times(0)).existComment(any());
      then(videoReadService).should(times(0)).existVideo(any());
      then(shortReadService).should(times(1)).existShort(any());
      // crossCheck(cmd, true)
      then(dislikeReadService).should(times(1)).getByCreateCmd(cmd);
      then(dislikeWriteService).should(times(0)).cancel(any());
      then(likeReadService).should(times(0)).getByCreateCmd(any());
      then(likeWriteService).should(times(0)).cancel((Long) any());
      // likeWriteService.like(cmd);
      then(likeWriteService).should(times(1)).like(cmd);
      then(dislikeWriteService).should(times(0)).dislike(any());
      // fluctuateLikeCount(cmd, isIncrease);
      then(commentWriteService).should(times(0)).increaseLikeCount(any());
      then(commentWriteService).should(times(0)).decreaseLikeCount(any());
      then(videoWriteService).should(times(0)).increaseLikeCount(any());
      then(videoWriteService).should(times(0)).decreaseLikeCount(any());
      then(shortWriteService).should(times(1)).increaseLikeCount(cmd.videoId());
      then(shortWriteService).should(times(0)).decreaseLikeCount(any());
    }

    @DisplayName("[short: like increase success with dislike] 쇼츠 좋아요 생성시 싫어요 삭제 성공 테스트")
    @Test
    public void executeShortLikeWithDislikeTest() {
      var videoType = VideoTypes.SHORTS_TYPE.getValue();
      var cmd = CreateLikeCommandFixtureFactory.create(videoType);
      var dislikeDto = LikeFixtureFactory.toDto(LikeFixtureFactory.create(videoType));

      given(shortReadService.existShort(any())).willReturn(true);
      given(dislikeReadService.getByCreateCmd(any())).willReturn(dislikeDto);

      createLikeUsecase.executeLike(cmd);

      // check(cmd);
      then(commentReadService).should(times(0)).existComment(any());
      then(videoReadService).should(times(0)).existVideo(any());
      then(shortReadService).should(times(1)).existShort(cmd.videoId());
      // crossCheck(cmd, true);
      then(dislikeReadService).should(times(1)).getByCreateCmd(cmd);
      then(dislikeWriteService).should(times(1)).cancel(dislikeDto.id());
      then(likeReadService).should(times(0)).getByCreateCmd(any());
      then(likeWriteService).should(times(0)).cancel((Long) any());
      // likeWriteService.like(cmd);
      then(likeWriteService).should(times(1)).like(cmd);
      then(dislikeWriteService).should(times(0)).dislike(any());
      // fluctuateLikeCount(cmd, isIncrease);
      then(commentWriteService).should(times(0)).increaseLikeCount(any());
      then(commentWriteService).should(times(0)).decreaseLikeCount(any());
      then(videoWriteService).should(times(0)).increaseLikeCount(any());
      then(videoWriteService).should(times(0)).decreaseLikeCount(any());
      then(shortWriteService).should(times(1)).increaseLikeCount(cmd.videoId());
      then(shortWriteService).should(times(0)).decreaseLikeCount(any());
    }

    @DisplayName("[comment: unsupported like case] 지원하지 않는 like case 실패 테스트")
    @Test
    public void executeUnSupportedLikeCaseTest() {
      var videoType = VideoTypes.COMMENT_TYPE.getValue();
      var cmd = CreateLikeCommandFixtureFactory.create(videoType, null);

      try {
        createLikeUsecase.executeLike(cmd);
      } catch (Exception e) {
        assertEquals(ErrorCode.UNSUPPORTED_LIKE_CASE.getMessage(), e.getMessage());
        // check(cmd);
        then(commentReadService).should(times(0)).existComment(any());
        then(videoReadService).should(times(0)).existVideo(any());
        then(shortReadService).should(times(0)).existShort(any());
        // crossCheck(cmd, true);
        then(dislikeReadService).should(times(0)).getByCreateCmd(any());
        then(dislikeWriteService).should(times(0)).cancel(any());
        then(likeReadService).should(times(0)).getByCreateCmd(any());
        then(likeWriteService).should(times(0)).cancel((Long) any());
        // likeWriteService.like(cmd);
        then(likeWriteService).should(times(0)).like(cmd);
        then(dislikeWriteService).should(times(0)).dislike(any());
        // fluctuateLikeCount(cmd, isIncrease);
        then(commentWriteService).should(times(0)).increaseLikeCount(any());
        then(commentWriteService).should(times(0)).decreaseLikeCount(any());
        then(videoWriteService).should(times(0)).increaseLikeCount(any());
        then(videoWriteService).should(times(0)).decreaseLikeCount(any());
        then(shortWriteService).should(times(0)).increaseLikeCount(any());
        then(shortWriteService).should(times(0)).decreaseLikeCount(any());
      }
    }

    @DisplayName("[video: like increase success without dislike] 비디오 좋아요 생성 성공 테스트")
    @Test
    public void executeVideoLikeTest() {
      var videoType = VideoTypes.VIDEO_TYPE.getValue();
      var cmd = CreateLikeCommandFixtureFactory.create(videoType);

      given(videoReadService.existVideo(any())).willReturn(true);
      given(dislikeReadService.getByCreateCmd(any())).willReturn(null);

      createLikeUsecase.executeLike(cmd);

      // check(cmd);
      then(commentReadService).should(times(0)).existComment(any());
      then(videoReadService).should(times(1)).existVideo(cmd.videoId());
      then(shortReadService).should(times(0)).existShort(any());
      // crossCheck(cmd, true);
      then(dislikeReadService).should(times(1)).getByCreateCmd(cmd);
      then(dislikeWriteService).should(times(0)).cancel(any());
      then(likeReadService).should(times(0)).getByCreateCmd(any());
      then(likeWriteService).should(times(0)).cancel((Long) any());
      // likeWriteService.like(cmd);
      then(likeWriteService).should(times(1)).like(cmd);
      then(dislikeWriteService).should(times(0)).dislike(any());
      // fluctuateLikeCount(cmd, isIncrease);
      then(commentWriteService).should(times(0)).increaseLikeCount(any());
      then(commentWriteService).should(times(0)).decreaseLikeCount(any());
      then(videoWriteService).should(times(1)).increaseLikeCount(cmd.videoId());
      then(videoWriteService).should(times(0)).decreaseLikeCount(any());
      then(shortWriteService).should(times(0)).increaseLikeCount(any());
      then(shortWriteService).should(times(0)).decreaseLikeCount(any());
    }

    @DisplayName("[video: like increase success with dislike] 비디오 좋아요 생성시 싫어요 삭제 성공 테스트")
    @Test
    public void executeVideoLikeWithDislikeTest() {
      var videoType = VideoTypes.VIDEO_TYPE.getValue();
      var cmd = CreateLikeCommandFixtureFactory.create(videoType);
      var dislikeDto = LikeFixtureFactory.toDto(LikeFixtureFactory.create(videoType));

      given(videoReadService.existVideo(any())).willReturn(true);
      given(dislikeReadService.getByCreateCmd(any())).willReturn(dislikeDto);

      createLikeUsecase.executeLike(cmd);

      // check(cmd);
      then(commentReadService).should(times(0)).existComment(any());
      then(videoReadService).should(times(1)).existVideo(cmd.videoId());
      then(shortReadService).should(times(0)).existShort(any());
      // crossCheck(cmd, true);
      then(dislikeReadService).should(times(1)).getByCreateCmd(cmd);
      then(dislikeWriteService).should(times(1)).cancel(dislikeDto.id());
      then(likeReadService).should(times(0)).getByCreateCmd(any());
      then(likeWriteService).should(times(0)).cancel((Long) any());
      // likeWriteService.like(cmd);
      then(likeWriteService).should(times(1)).like(cmd);
      then(dislikeWriteService).should(times(0)).dislike(any());
      // fluctuateLikeCount(cmd, isIncrease);
      then(commentWriteService).should(times(0)).increaseLikeCount(any());
      then(commentWriteService).should(times(0)).decreaseLikeCount(any());
      then(videoWriteService).should(times(1)).increaseLikeCount(cmd.videoId());
      then(videoWriteService).should(times(0)).decreaseLikeCount(any());
      then(shortWriteService).should(times(0)).increaseLikeCount(any());
      then(shortWriteService).should(times(0)).decreaseLikeCount(any());
    }
  }
}
