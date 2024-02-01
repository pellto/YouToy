package com.pellto.youtoy.comment.domain.service;

import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.comment.domain.model.CommentContentsType;
import com.pellto.youtoy.comment.domain.port.out.persistence.SaveCommentPort;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@Tag("service")
class CommentPostServiceTest {

  private static final String SERVICE_NAME = "CommentPostService";

  @InjectMocks
  private CommentPostService commentPostService;

  @Mock
  private SaveCommentPort saveCommentPort;

  @DisplayName("[" + SERVICE_NAME + "/removeAllByPostId] removeAllByPostId 성공 테스트")
  @Test
  void removeAllByPostIdSuccessTest() {
    var contentsType = CommentContentsType.POST;
    var postId = 1L;

    commentPostService.removeAllByPostId(postId);

    then(saveCommentPort).should(times(1))
        .deleteAllByContentsIdAndContentsType(postId, contentsType);
  }
}