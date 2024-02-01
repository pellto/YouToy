package com.pellto.youtoy.comment.domain.service;

import static org.mockito.BDDMockito.given;

import com.pellto.youtoy.comment.domain.model.Reply;
import com.pellto.youtoy.comment.domain.port.out.persistence.LoadReplyPort;
import com.pellto.youtoy.comment.util.ReplyFixtureFactory;
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
class ReplyReadServiceTest {

  private static final String SERVICE_NAME = "ReplyReadService";

  @InjectMocks
  private ReplyReadService replyReadService;

  @Mock
  private LoadReplyPort loadReplyPort;

  @DisplayName("[" + SERVICE_NAME
      + "/readAllByParentCommentId] readAllByParentCommentId 성공 테스트")
  @Test
  void readAllByParentCommentIdSuccessTest() {
    var reply1 = ReplyFixtureFactory.create();
    var reply2 = ReplyFixtureFactory.create(2L);
    var replies = new ArrayList<Reply>();
    replies.add(reply1);
    replies.add(reply2);

    given(loadReplyPort.loadAllByParentCommentId(reply1.getParentCommentId())).willReturn(replies);

    var loadedReplies = replyReadService.readAllByParentCommentId(reply1.getParentCommentId());

    Assertions.assertThat(loadedReplies).isNotNull();
    Assertions.assertThat(loadedReplies.size()).isEqualTo(replies.size());
    Assertions.assertThat(loadedReplies).usingRecursiveComparison()
        .isEqualTo(replies.stream().map(Reply::toDto).toList());
  }
}