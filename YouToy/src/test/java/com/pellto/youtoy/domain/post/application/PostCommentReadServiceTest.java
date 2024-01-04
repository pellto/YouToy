package com.pellto.youtoy.domain.post.application;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.domain.post.domain.PostComment;
import com.pellto.youtoy.domain.post.dto.PostCommentDto;
import com.pellto.youtoy.domain.post.repository.PostCommentRepository;
import com.pellto.youtoy.domain.post.util.PostCommentFactory;
import java.util.ArrayList;
import java.util.Optional;
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
class PostCommentReadServiceTest {

  @InjectMocks
  private PostCommentReadService commentReadService;

  @Mock
  private PostCommentRepository commentRepository;

  @DisplayName("[commentReadService: findAll: success] 댓글 전체 조회 테스트")
  @Test
  void findAllSuccessTest() {
    var comment = PostCommentFactory.createCommunityComment();
    var commentList = new ArrayList<PostComment>();
    commentList.add(comment);

    given(commentRepository.findAll()).willReturn(commentList);

    var foundCommentList = commentReadService.findAll();

    then(commentRepository).should(times(1)).findAll();
    Assertions.assertThat(foundCommentList).isNotNull();
    Assertions.assertThat(foundCommentList).isNotEmpty();
    Assertions.assertThat(foundCommentList.size()).isEqualTo(commentList.size());
    Assertions.assertThat(foundCommentList.get(0).getClass()).isEqualTo(PostCommentDto.class);
  }

  @DisplayName("[commentReadService: findById: success] 댓글 id 조건 조회 테스트")
  @Test
  void findByIdSuccessTest() {
    var comment = PostCommentFactory.createCommunityComment();

    given(commentRepository.findById(comment.getId())).willReturn(Optional.of(comment));

    var foundComment = commentReadService.findById(comment.getId());

    then(commentRepository).should(times(1)).findById(comment.getId());
    Assertions.assertThat(foundComment).isNotNull();
    Assertions.assertThat(foundComment.id()).isEqualTo(comment.getId());
    Assertions.assertThat(foundComment.getClass()).isEqualTo(PostCommentDto.class);
  }
}