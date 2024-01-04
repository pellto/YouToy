package com.pellto.youtoy.domain.post.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.domain.post.repository.PostReplyRepository;
import com.pellto.youtoy.domain.post.util.CommunityCommentFactory;
import com.pellto.youtoy.domain.post.util.PostReplyCommentFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@Tag("service")
@ExtendWith(MockitoExtension.class)
class PostReplyWriteServiceTest {

  @InjectMocks
  private PostReplyWriteService replyCommentWriteService;
  @Mock
  private PostReplyRepository replyRepository;
  @Mock
  private PostReplyReadService replyCommentReadService;
  @Mock
  private PostCommentReadService commentReadService;

  @DisplayName("[replyCommentWriteService: save: success] 답글 저장 테스트")
  @Test
  void writeSuccessTest() {
    var parentComment = CommunityCommentFactory.createCommunityComment();
    var req = PostReplyCommentFactory.createWriteReplyRequest();
    var reply = PostReplyCommentFactory.createReplyComment();
    var replyDto = PostReplyCommentFactory.createReplyCommentDto();

    given(commentReadService.getById(any())).willReturn(parentComment);
    given(replyRepository.save(any())).willReturn(reply);
    given(replyCommentReadService.toDto(reply)).willReturn(replyDto);

    var writtenReply = replyCommentWriteService.write(req);

    then(commentReadService).should(times(1)).getById(any());
    then(replyRepository).should(times(1)).save(any());
    then(replyCommentReadService).should(times(1)).toDto(reply);
    Assertions.assertThat(writtenReply).isEqualTo(replyDto);
  }
}