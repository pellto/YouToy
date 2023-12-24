package com.pellto.youtoy.domain.community.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.domain.community.repository.CommunityPostRepository;
import com.pellto.youtoy.domain.community.util.CommunityPostFactory;
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
class CommunityPostWriteServiceTest {

  @InjectMocks
  private CommunityPostWriteService postWriteService;
  @Mock
  private CommentPostReadService postReadService;
  @Mock
  private CommunityPostRepository postRepository;

  @DisplayName("[communityPostWriteService: writePost: success] 게시글 생성 성공 테스트")
  @Test
  void writePostSuccessTest() {
    var req = CommunityPostFactory.createWritePostRequest();
    var post = CommunityPostFactory.createPost();
    var postDto = CommunityPostFactory.createPostDto();

    given(postRepository.save(any())).willReturn(post);
    given(postReadService.toDto(post)).willReturn(postDto);

    var writtenPost = postWriteService.writePost(req);

    then(postRepository).should(times(1)).save(any());
    then(postReadService).should(times(1)).toDto(post);
    Assertions.assertThat(writtenPost).isNotNull();
    Assertions.assertThat(writtenPost).isEqualTo(postDto);
  }
}