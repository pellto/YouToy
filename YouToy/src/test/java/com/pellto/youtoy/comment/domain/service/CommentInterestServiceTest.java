package com.pellto.youtoy.comment.domain.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.comment.domain.port.out.persistence.LoadCommentPort;
import com.pellto.youtoy.comment.domain.port.out.persistence.SaveCommentPort;
import com.pellto.youtoy.comment.util.CommentFixtureFactory;
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
class CommentInterestServiceTest {

  private static final String SERVICE_NAME = "CommentInterestService";
  @InjectMocks
  private CommentInterestService commentInterestService;

  @Mock
  private LoadCommentPort loadCommentPort;
  @Mock
  private SaveCommentPort saveCommentPort;


  @DisplayName("[" + SERVICE_NAME + "/increaseLikeCount] increaseLikeCount 성공 테스트")
  @Test
  void increaseLikeCountSuccessTest() {
    var comment = CommentFixtureFactory.create();
    given(loadCommentPort.load(comment.getId())).willReturn(comment);

    commentInterestService.increaseLikeCount(comment.getId());

    Assertions.assertThat(comment.getLikeCount()).isEqualTo(1L);
    then(loadCommentPort).should(times(1)).load(comment.getId());
    then(saveCommentPort).should(times(1)).save(any());
  }

  @DisplayName("[" + SERVICE_NAME + "/decreaseLikeCount] decreaseLikeCount 성공 테스트")
  @Test
  void decreaseLikeCountSuccessTest() {
    var comment = CommentFixtureFactory.createWithLikeCount(1L);
    given(loadCommentPort.load(comment.getId())).willReturn(comment);

    commentInterestService.decreaseLikeCount(comment.getId());

    Assertions.assertThat(comment.getLikeCount()).isEqualTo(0);
    then(loadCommentPort).should(times(1)).load(comment.getId());
    then(saveCommentPort).should(times(1)).save(any());
  }
}