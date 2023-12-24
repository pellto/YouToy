package com.pellto.youtoy.domain.community.application;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.domain.community.domain.CommunityPost;
import com.pellto.youtoy.domain.community.dto.CommunityPostDto;
import com.pellto.youtoy.domain.community.repository.CommunityPostRepository;
import com.pellto.youtoy.domain.community.util.CommentPostFactory;
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
class CommentPostReadServiceTest {

  @InjectMocks
  private CommentPostReadService postReadService;

  @Mock
  private CommunityPostRepository postRepository;

  @DisplayName("[commentPostReadService: findAll: success] 게시글 전체 조회 성공 테스트")
  @Test
  void findAllSuccessTest() {
    var post = CommentPostFactory.createPost();
    var postList = new ArrayList<CommunityPost>();
    postList.add(post);

    given(postRepository.findAll()).willReturn(postList);

    var foundPostList = postReadService.findAll();

    then(postRepository).should(times(1)).findAll();
    Assertions.assertThat(foundPostList).isNotNull();
    Assertions.assertThat(foundPostList).isNotEmpty();
    Assertions.assertThat(foundPostList.size()).isEqualTo(1);
    Assertions.assertThat(foundPostList.get(0).getClass()).isEqualTo(CommunityPostDto.class);
  }

  @DisplayName("[commentPostReadService: findById: success] 게시글 전체 조회 성공 테스트")
  @Test
  void findByIdSuccessTest() {
    var post = CommentPostFactory.createPost();

    given(postRepository.findById(any())).willReturn(Optional.ofNullable(post));

    var foundPost = postReadService.findById(post.getId());

    then(postRepository).should(times(1)).findById(post.getId());
    Assertions.assertThat(foundPost).isNotNull();
    Assertions.assertThat(foundPost.id()).isEqualTo(post.getId());
    Assertions.assertThat(foundPost.getClass()).isEqualTo(CommunityPostDto.class);
  }
}