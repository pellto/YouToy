package com.pellto.youtoy.domain.community.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.domain.community.domain.PostReplyComment;
import com.pellto.youtoy.domain.community.dto.PostReplyCommentDto;
import com.pellto.youtoy.domain.community.repository.PostReplyRepository;
import com.pellto.youtoy.domain.community.util.CommunityCommentFactory;
import com.pellto.youtoy.domain.community.util.PostReplyCommentFactory;
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
class PostReplyCommentReadServiceTest {

  @InjectMocks
  private PostReplyCommentReadService postReplyCommentReadService;
  @Mock
  private PostReplyRepository replyRepository;
  @Mock
  private CommunityCommentReadService parentCommentReadService;

  @DisplayName("[postReplyCommentReadService: findAll: success] 답글 전체 조회 테스트")
  @Test
  void findAllSuccessTest() {
    var reply = PostReplyCommentFactory.createReplyComment();
    var replyList = new ArrayList<PostReplyComment>();
    replyList.add(reply);

    given(replyRepository.findAll()).willReturn(replyList);

    var foundReplyList = postReplyCommentReadService.findAll();

    then(replyRepository).should(times(1)).findAll();
    Assertions.assertThat(foundReplyList).isNotNull();
    Assertions.assertThat(foundReplyList).isNotEmpty();
    Assertions.assertThat(foundReplyList.size()).isEqualTo(replyList.size());
    Assertions.assertThat(foundReplyList.get(0).getClass()).isEqualTo(PostReplyCommentDto.class);
  }

  @DisplayName("[postReplyCommentReadService: findAllByParentId: success] 댓글 parentId 조건 전체 조회 테스트")
  @Test
  void findAllByParentIdSuccessTest() {
    var parentComment = CommunityCommentFactory.createCommunityComment();
    var reply = PostReplyCommentFactory.createReplyComment(parentComment);
    var replyList = new ArrayList<PostReplyComment>();
    replyList.add(reply);

    given(parentCommentReadService.getById(parentComment.getId())).willReturn(parentComment);
    given(replyRepository.findAllByParentComment(any())).willReturn(replyList);

    var foundReplyList = postReplyCommentReadService.findAllByParentId(
        reply.getParentComment().getId());

    then(replyRepository).should(times(1)).findAllByParentComment(any());
    Assertions.assertThat(foundReplyList).isNotNull();
    Assertions.assertThat(foundReplyList).isNotEmpty();
    Assertions.assertThat(foundReplyList.size()).isEqualTo(replyList.size());
    Assertions.assertThat(foundReplyList.get(0).getClass()).isEqualTo(PostReplyCommentDto.class);
  }

}