package com.pellto.youtoy.comment.domain.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.comment.domain.port.out.persistence.LoadCommentPort;
import com.pellto.youtoy.comment.domain.port.out.persistence.LoadReplyPort;
import com.pellto.youtoy.comment.domain.port.out.persistence.SaveCommentPort;
import com.pellto.youtoy.comment.domain.port.out.persistence.SaveReplyPort;
import com.pellto.youtoy.comment.util.CommentFixtureFactory;
import java.util.ArrayList;
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
class CommentReplyServiceTest {

  private static final String SERVICE_NAME = "CommentReplyServiceTest";
  @InjectMocks
  private CommentReplyService commentReplyService;

  @Mock
  private LoadCommentPort loadCommentPort;
  @Mock
  private SaveCommentPort saveCommentPort;
  @Mock
  private LoadReplyPort loadReplyPort;
  @Mock
  private SaveReplyPort saveReplyPort;

  @DisplayName("[" + SERVICE_NAME + "/increaseCommentReplyCount] increaseCommentReplyCount 성공 테스트")
  @Test
  void increaseCommentReplyCountSuccessTest() {
    var comment = CommentFixtureFactory.create();
    given(loadCommentPort.load(comment.getId())).willReturn(comment);

    commentReplyService.increaseCommentReplyCount(comment.getId());

    Assertions.assertThat(comment.getReplyCount()).isEqualTo(1L);
    then(loadCommentPort).should(times(1)).load(comment.getId());
    then(saveCommentPort).should(times(1)).save(any());
  }

  @DisplayName("[" + SERVICE_NAME + "/decreaseCommentReplyCount] decreaseCommentReplyCount 성공 테스트")
  @Test
  void decreaseCommentReplyCountSuccessTest() {
    var comment = CommentFixtureFactory.createWithReplyCount(1L);
    given(loadCommentPort.load(comment.getId())).willReturn(comment);

    commentReplyService.decreaseCommentReplyCount(comment.getId());

    Assertions.assertThat(comment.getReplyCount()).isEqualTo(0L);
    then(loadCommentPort).should(times(1)).load(comment.getId());
    then(saveCommentPort).should(times(1)).save(any());
  }

  @DisplayName("[" + SERVICE_NAME
      + "/removeAllRepliesByParentCommentId] removeAllRepliesByParentCommentId 성공 테스트")
  @Test
  void removeAllRepliesByParentCommentIdSuccessTest() {
    var removedCommentId = 1L;
    var replyId1 = 1L;
    var replyId2 = 2L;
    var replyIds = new ArrayList<Long>();
    replyIds.add(replyId1);
    replyIds.add(replyId2);

    given(loadReplyPort.loadAllIdsByParentCommentId(removedCommentId)).willReturn(replyIds);

    commentReplyService.removeAllRepliesByParentCommentId(removedCommentId);

    then(loadReplyPort).should(times(1)).loadAllIdsByParentCommentId(removedCommentId);
    then(saveReplyPort).should(times(replyIds.size())).deleteById(any());
  }

}