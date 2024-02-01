package com.pellto.youtoy.comment.domain.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.comment.domain.model.Comment;
import com.pellto.youtoy.comment.domain.model.CommentContentsType;
import com.pellto.youtoy.comment.domain.port.out.http.ContentsExistHandlePort;
import com.pellto.youtoy.comment.domain.port.out.persistence.LoadCommentPort;
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
class CommentReadServiceTest {

  private static final String SERVICE_NAME = "CommentReadService";

  @InjectMocks
  private CommentReadService commentReadService;

  @Mock
  private LoadCommentPort loadCommentPort;
  @Mock
  private ContentsExistHandlePort contentsExistHandlePort;

  @DisplayName("[" + SERVICE_NAME
      + "/readAllByPOSTAndContentsId] readAllByPOSTAndContentsId POST 성공 테스트")
  @Test
  void readAllByPostAndContentsIdSuccessTest() {
    var contentsType = CommentContentsType.POST.getType();
    var comment1 = CommentFixtureFactory.create(contentsType);
    var comment2 = CommentFixtureFactory.create(contentsType, 2L);
    var comments = new ArrayList<Comment>();
    comments.add(comment1);
    comments.add(comment2);
    var contentsId = comment1.getContentsId();
    var commentDtos = comments.stream().map(Comment::toDto).toList();

    given(contentsExistHandlePort.isExistContents(contentsType, contentsId)).willReturn(true);
    given(loadCommentPort.loadAllByContentsTypeAndContentsId(contentsType, contentsId)).willReturn(
        comments);

    var loadedComments = commentReadService.readAllByContentsTypeAndContentsId(
        contentsType, contentsId);

    Assertions.assertThat(loadedComments).usingRecursiveComparison().isEqualTo(commentDtos);
    then(contentsExistHandlePort).should(times(1)).isExistContents(contentsType, contentsId);
    then(loadCommentPort).should(times(1))
        .loadAllByContentsTypeAndContentsId(contentsType, contentsId);
  }

  @DisplayName("[" + SERVICE_NAME
      + "/readAllByPOSTAndContentsId] readAllByPOSTAndContentsId POST not exist 테스트")
  @Test
  void readAllByPostAndContentsIdFailWithNotExistTest() {
    var contentsType = CommentContentsType.POST.getType();
    var comment1 = CommentFixtureFactory.create(contentsType);
    var contentsId = comment1.getContentsId();

    given(contentsExistHandlePort.isExistContents(contentsType, contentsId)).willReturn(false);

    Assertions.assertThatThrownBy(
            () -> commentReadService.readAllByContentsTypeAndContentsId(contentsType, contentsId))
        .isInstanceOf(IllegalArgumentException.class).hasMessage("해당 컨텐츠가 존재하지 않습니다.");
    then(contentsExistHandlePort).should(times(1)).isExistContents(contentsType, contentsId);
    then(loadCommentPort).should(times(0))
        .loadAllByContentsTypeAndContentsId(any(), any());
  }

  @DisplayName("[" + SERVICE_NAME + "/isExistCommentById] isExistCommentById true 성공 테스트")
  @Test
  void isExistCommentByIdTrueSuccessTest() {
    given(loadCommentPort.isExistById(1L)).willReturn(true);

    var isExist = commentReadService.isExistCommentById(1L);

    Assertions.assertThat(isExist).isTrue();
    then(loadCommentPort).should(times(1)).isExistById(1L);
  }

  @DisplayName("[" + SERVICE_NAME + "/isExistCommentById] isExistCommentById false 성공 테스트")
  @Test
  void isExistCommentByIdFalseSuccessTest() {
    given(loadCommentPort.isExistById(1L)).willReturn(false);

    var isExist = commentReadService.isExistCommentById(1L);

    Assertions.assertThat(isExist).isFalse();
    then(loadCommentPort).should(times(1)).isExistById(1L);
  }
}