package com.pellto.youtoy.comment.domain.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.comment.domain.port.out.persistence.LoadReplyPort;
import com.pellto.youtoy.comment.domain.port.out.persistence.SaveReplyPort;
import com.pellto.youtoy.comment.util.ReplyFixtureFactory;
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
class ReplyInterestServiceTest {

  private static final String SERVICE_NAME = "ReplyInterestService";
  @InjectMocks
  private ReplyInterestService replyInterestService;

  @Mock
  private LoadReplyPort loadReplyPort;
  @Mock
  private SaveReplyPort saveReplyPort;


  @DisplayName("[" + SERVICE_NAME + "/increaseLikeCount] increaseLikeCount 성공 테스트")
  @Test
  void increaseLikeCountSuccessTest() {
    var reply = ReplyFixtureFactory.create();
    given(loadReplyPort.load(reply.getId())).willReturn(reply);

    replyInterestService.increaseLikeCount(reply.getId());

    Assertions.assertThat(reply.getLikeCount()).isEqualTo(1L);
    then(loadReplyPort).should(times(1)).load(reply.getId());
    then(saveReplyPort).should(times(1)).save(any());
  }

  @DisplayName("[" + SERVICE_NAME + "/decreaseLikeCount] decreaseLikeCount 성공 테스트")
  @Test
  void decreaseLikeCountSuccessTest() {
    var reply = ReplyFixtureFactory.createWithLikeCount(1L);
    given(loadReplyPort.load(reply.getId())).willReturn(reply);

    replyInterestService.decreaseLikeCount(reply.getId());

    Assertions.assertThat(reply.getLikeCount()).isEqualTo(0);
    then(loadReplyPort).should(times(1)).load(reply.getId());
    then(saveReplyPort).should(times(1)).save(any());
  }
}