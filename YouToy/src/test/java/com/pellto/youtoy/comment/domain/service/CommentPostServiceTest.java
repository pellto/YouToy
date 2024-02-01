package com.pellto.youtoy.comment.domain.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.comment.domain.model.CommentContentsType;
import com.pellto.youtoy.comment.domain.port.out.event.CommentEventPort;
import com.pellto.youtoy.comment.domain.port.out.persistence.LoadCommentPort;
import com.pellto.youtoy.comment.domain.port.out.persistence.SaveCommentPort;
import java.util.ArrayList;
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

  @Mock
  private LoadCommentPort loadCommentPort;
  @Mock
  private CommentEventPort commentEventPort;

  @DisplayName("[" + SERVICE_NAME + "/removeAllByPostId] removeAllByPostId 성공 테스트")
  @Test
  void removeAllByPostIdSuccessTest() {
    var contentsType = CommentContentsType.POST;
    var postId = 1L;
    var willRemoveCommentIds = new ArrayList<Long>();
    willRemoveCommentIds.add(1L);
    willRemoveCommentIds.add(2L);

    given(loadCommentPort.loadAllIdsByContentsTypeAndContentsId(contentsType.getType(),
        postId)).willReturn(willRemoveCommentIds);

    commentPostService.removeAllByPostId(postId);

    then(saveCommentPort).should(times(1))
        .deleteAllByContentsIdAndContentsType(postId, contentsType);
    then(loadCommentPort).should(times(1))
        .loadAllIdsByContentsTypeAndContentsId(contentsType.getType(), postId);
    then(commentEventPort).should(times(willRemoveCommentIds.size())).commentRemovedEvent(any());
  }
}