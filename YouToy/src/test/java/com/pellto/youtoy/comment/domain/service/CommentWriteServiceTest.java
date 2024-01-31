package com.pellto.youtoy.comment.domain.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.comment.domain.port.out.event.CommentEventPort;
import com.pellto.youtoy.comment.domain.port.out.http.ChannelHandlePort;
import com.pellto.youtoy.comment.domain.port.out.http.ContentsExistHandlePort;
import com.pellto.youtoy.comment.domain.port.out.persistence.LoadCommentPort;
import com.pellto.youtoy.comment.domain.port.out.persistence.SaveCommentPort;
import com.pellto.youtoy.comment.util.CommentFixtureFactory;
import com.pellto.youtoy.global.dto.comment.CommentDto;
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
class CommentWriteServiceTest {

  private static final String SERVICE_NAME = "CommentWriteService";

  @InjectMocks
  private CommentWriteService commentWriteService;

  @Mock
  private LoadCommentPort loadCommentPort;
  @Mock
  private SaveCommentPort saveCommentPort;
  @Mock
  private CommentEventPort commentEventPort;
  @Mock
  private ContentsExistHandlePort contentsExistHandlePort;
  @Mock
  private ChannelHandlePort channelHandlePort;

  @DisplayName("[" + SERVICE_NAME + "/write] write 성공 테스트")
  @Test
  void writeSuccessTest() {
    var request = CommentFixtureFactory.createWriteCommentRequest();
    var commenterChannelInfo = CommentFixtureFactory.createGetCommenterChannelInfoResponse();
    var comment = CommentFixtureFactory.create();

    given(contentsExistHandlePort.isExistContents(request.contentsType(),
        request.contentsId())).willReturn(true);
    given(channelHandlePort.getCommenterChannelInfo(request.commenterId())).willReturn(
        commenterChannelInfo);
    given(saveCommentPort.save(any())).willReturn(comment);

    var writtenComment = commentWriteService.write(request);

    Assertions.assertThat(writtenComment).isNotNull();
    Assertions.assertThat(writtenComment.id()).isNotNull();
    Assertions.assertThat(writtenComment.getClass()).isEqualTo(CommentDto.class);
    Assertions.assertThat(writtenComment).usingRecursiveComparison().isEqualTo(comment.toDto());
    then(contentsExistHandlePort).should(times(1)).isExistContents(any(), any());
    then(channelHandlePort).should(times(1)).getCommenterChannelInfo(any());
    then(saveCommentPort).should(times(1)).save(any());
    then(commentEventPort).should(times(1)).commentWrittenEvent(any());
  }

  @DisplayName("[" + SERVICE_NAME + "/write] write 실패 테스트")
  @Test
  void writeFailTest() {
    var request = CommentFixtureFactory.createWriteCommentRequest();

    given(contentsExistHandlePort.isExistContents(request.contentsType(),
        request.contentsId())).willReturn(false);

    Assertions.assertThatThrownBy(() -> commentWriteService.write(request))
        .isInstanceOf(IllegalArgumentException.class).hasMessage("해당 컨텐츠가 존재하지 않습니다.");
    then(contentsExistHandlePort).should(times(1)).isExistContents(any(), any());
    then(channelHandlePort).should(times(0)).getCommenterChannelInfo(any());
    then(saveCommentPort).should(times(0)).save(any());
    then(commentEventPort).should(times(0)).commentWrittenEvent(any());
  }

  @DisplayName("[" + SERVICE_NAME + "/change] change 성공 테스트")
  @Test
  void changeSuccessTest() {
    var request = CommentFixtureFactory.createChangeCommentRequest();
    var beforeComment = CommentFixtureFactory.create(request.commentId());
    var afterComment = CommentFixtureFactory.createChangedComment();

    given(loadCommentPort.load(request.commentId())).willReturn(beforeComment);

    var changedComment = commentWriteService.change(request);

    Assertions.assertThat(changedComment).usingRecursiveComparison()
        .ignoringFields("updatedAt")
        .isEqualTo(afterComment.toDto());
    Assertions.assertThat(changedComment.updatedAt()).isNotEqualTo(changedComment.createdAt());
    then(loadCommentPort).should(times(1)).load(any());
    then(saveCommentPort).should(times(1)).update(any());
    then(commentEventPort).should(times(1)).commentChangedEvent(any(), any());
  }
}