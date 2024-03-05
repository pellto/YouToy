package com.pellto.youtoy.comment.domain.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.comment.domain.port.out.event.ReplyEventPort;
import com.pellto.youtoy.comment.domain.port.out.http.ChannelHandlePort;
import com.pellto.youtoy.comment.domain.port.out.persistence.LoadCommentPort;
import com.pellto.youtoy.comment.domain.port.out.persistence.LoadReplyPort;
import com.pellto.youtoy.comment.domain.port.out.persistence.SaveReplyPort;
import com.pellto.youtoy.comment.util.ReplyFixtureFactory;
import com.pellto.youtoy.global.dto.comment.ReplyDto;
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
class ReplyWriteServiceTest {

  private static final String SERVICE_NAME = "ReplyWriteService";

  @InjectMocks
  private ReplyWriteService replyWriteService;

  @Mock
  private LoadCommentPort loadCommentPort;
  @Mock
  private LoadReplyPort loadReplyPort;
  @Mock
  private ReplyEventPort replyEventPort;
  @Mock
  private SaveReplyPort saveReplyPort;
  @Mock
  private ChannelHandlePort channelHandlePort;

  @DisplayName("[" + SERVICE_NAME + "/writeReply] writeReply 성공 테스트")
  @Test
  void writeReplySuccessTest() {
    var request = ReplyFixtureFactory.createWriteReplyRequest();
    var reply = ReplyFixtureFactory.create(request);
    var response = ReplyFixtureFactory.createGetCommenterChannelInfoResponse(request);

    given(loadCommentPort.isExistById(request.parentCommentId())).willReturn(true);
    given(channelHandlePort.getCommenterChannelInfo(request.replierId())).willReturn(response);
    given(saveReplyPort.save(any())).willReturn(reply);

    var writtenReply = replyWriteService.writeReply(request);

    Assertions.assertThat(writtenReply).isNotNull();
    Assertions.assertThat(writtenReply.id()).isNotNull();
    Assertions.assertThat(writtenReply.getClass()).isEqualTo(ReplyDto.class);
    Assertions.assertThat(writtenReply).usingRecursiveComparison().isEqualTo(reply.toDto());
    then(loadCommentPort).should(times(1)).isExistById(any());
    then(channelHandlePort).should(times(1)).getCommenterChannelInfo(any());
    then(saveReplyPort).should(times(1)).save(any());
    then(replyEventPort).should(times(1)).replyWrittenEvent(any());
  }

  @DisplayName("[" + SERVICE_NAME + "/writeReply] writeReply 부모 댓글 없음 실패 테스트")
  @Test
  void writeReplyFailWithNotExistParentCommentTest() {
    var request = ReplyFixtureFactory.createWriteReplyRequest();

    given(loadCommentPort.isExistById(request.parentCommentId())).willReturn(false);

    Assertions.assertThatThrownBy(() -> replyWriteService.writeReply(request))
        .isInstanceOf(IllegalArgumentException.class).hasMessage("부모 댓글 없음");

    then(loadCommentPort).should(times(1)).isExistById(any());
    then(channelHandlePort).should(times(0)).getCommenterChannelInfo(any());
    then(saveReplyPort).should(times(0)).save(any());
    then(replyEventPort).should(times(0)).replyWrittenEvent(any());
  }

  @DisplayName("[" + SERVICE_NAME + "/removeReply] removeReply 성공 테스트")
  @Test
  void removeSuccessTest() {
    var reply = ReplyFixtureFactory.create();
    given(loadReplyPort.load(1L)).willReturn(reply);

    replyWriteService.removeReply(1L);

    then(saveReplyPort).should(times(1)).deleteById(reply.getId());
    then(replyEventPort).should(times(1)).replyRemovedEvent(reply.getId(), reply.getParentCommentId());
  }
}