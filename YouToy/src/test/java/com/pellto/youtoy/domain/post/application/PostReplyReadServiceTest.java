package com.pellto.youtoy.domain.post.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.domain.post.domain.PostReply;
import com.pellto.youtoy.domain.post.dto.PostReplyDto;
import com.pellto.youtoy.domain.post.repository.PostReplyRepository;
import com.pellto.youtoy.domain.post.util.PostCommentFactory;
import com.pellto.youtoy.domain.post.util.PostReplyFactory;
import java.util.ArrayList;
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
class PostReplyReadServiceTest {

  @InjectMocks
  private PostReplyReadService postReplyReadService;
  @Mock
  private PostReplyRepository replyRepository;
  @Mock
  private PostCommentReadService parentCommentReadService;

  @DisplayName("[postReplyReadService: findAll: success] 답글 전체 조회 테스트")
  @Test
  void findAllSuccessTest() {
    var reply = PostReplyFactory.create();
    var replyList = new ArrayList<PostReply>();
    replyList.add(reply);

    given(replyRepository.findAll()).willReturn(replyList);

    var foundReplyList = postReplyReadService.findAll();

    then(replyRepository).should(times(1)).findAll();
    Assertions.assertThat(foundReplyList).isNotNull();
    Assertions.assertThat(foundReplyList).isNotEmpty();
    Assertions.assertThat(foundReplyList.size()).isEqualTo(replyList.size());
    Assertions.assertThat(foundReplyList.get(0).getClass()).isEqualTo(PostReplyDto.class);
  }

  @DisplayName("[postReplyReadService: findAllByParentId: success] 댓글 parentId 조건 전체 조회 테스트")
  @Test
  void findAllByParentIdSuccessTest() {
    var parentComment = PostCommentFactory.createCommunityComment();
    var reply = PostReplyFactory.create(parentComment);
    var replyList = new ArrayList<PostReply>();
    replyList.add(reply);

    given(parentCommentReadService.getById(parentComment.getId())).willReturn(parentComment);
    given(replyRepository.findAllByParentComment(any())).willReturn(replyList);

    var foundReplyList = postReplyReadService.findAllByParentId(
        reply.getParentComment().getId());

    then(replyRepository).should(times(1)).findAllByParentComment(any());
    Assertions.assertThat(foundReplyList).isNotNull();
    Assertions.assertThat(foundReplyList).isNotEmpty();
    Assertions.assertThat(foundReplyList.size()).isEqualTo(replyList.size());
    Assertions.assertThat(foundReplyList.get(0).getClass()).isEqualTo(PostReplyDto.class);
  }

}