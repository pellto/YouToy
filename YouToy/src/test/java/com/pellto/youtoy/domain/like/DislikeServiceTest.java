package com.pellto.youtoy.domain.like;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.times;

import com.pellto.youtoy.domain.like.repository.DislikeRepository;
import com.pellto.youtoy.domain.like.service.DislikeWriteService;
import com.pellto.youtoy.util.error.ErrorCode;
import com.pellto.youtoy.util.like.CreateLikeCommandFixtureFactory;
import com.pellto.youtoy.util.like.DislikeFixtureFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@Tag("domain")
@ExtendWith(MockitoExtension.class)
public class DislikeServiceTest {

  private static final String DOMAIN = "Dislike";
  @InjectMocks
  private DislikeWriteService dislikeWriteService;

  @Mock
  private DislikeRepository dislikeRepository;

  @DisplayName("[" + DOMAIN + ": cancel: byId Not Exist] id로 존재하지 않는 dislike 취소 실패 테스트")
  @Test
  public void dislikeCancelByIdNotExistTest() {
    var likeId = 1L;

    given(dislikeRepository.existById(any())).willReturn(false);

    try {
      dislikeWriteService.cancel(likeId);
    } catch (Exception e) {
      assertEquals(e.getMessage(), ErrorCode.NOT_EXIST_DISLIKE.getMessage());
      then(dislikeRepository).should(times(1)).existById(any());
      then(dislikeRepository).should(times(0)).deleteById(any());
    }
  }

  @DisplayName("[" + DOMAIN + ": cancel: byId success] id로 dislike 취소 성공 테스트")
  @Test
  public void dislikeCancelByIdTest() {
    var likeId = 1L;

    given(dislikeRepository.existById(any())).willReturn(true);

    dislikeWriteService.cancel(likeId);

    then(dislikeRepository).should(times(1)).existById(any());
    then(dislikeRepository).should(times(1)).deleteById(any());
  }

  @DisplayName("[" + DOMAIN + ": dislike: success] dislike 생성 성공 테스트")
  @Test
  public void dislikeTest() {
    var cmd = CreateLikeCommandFixtureFactory.create();
    var dislike = DislikeFixtureFactory.create();

    given(dislikeRepository.save(any())).willReturn(dislike);

    var createdLike = dislikeWriteService.dislike(cmd);

    assertEquals(cmd.videoId(), createdLike.getVideoId());
    assertEquals(cmd.videoType(), createdLike.getVideoType());
    assertEquals(cmd.commentId(), createdLike.getCommentId());
    assertEquals(cmd.userId(), createdLike.getUserId());
    then(dislikeRepository).should(times(1)).save(any());
  }

  @DisplayName("[" + DOMAIN + ": dislike: Unsupported] 지원하지 않는 dislike 생성 실패 테스트")
  @Test
  public void dislikeUnSupportedTest() {
    var cmd = CreateLikeCommandFixtureFactory.create(null, 1L);

    try {
      dislikeWriteService.dislike(cmd);
    } catch (Exception e) {
      then(dislikeRepository).should(times(0)).save(any());
    }
  }
}
