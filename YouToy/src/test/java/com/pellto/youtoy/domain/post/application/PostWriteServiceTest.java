package com.pellto.youtoy.domain.post.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.domain.post.repository.PostRepository;
import com.pellto.youtoy.domain.post.util.PostFactory;
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
class PostWriteServiceTest {

  @InjectMocks
  private PostWriteService postWriteService;
  @Mock
  private PostReadService postReadService;
  @Mock
  private PostRepository postRepository;

  @DisplayName("[communityPostWriteService: writePost: success] 게시글 생성 성공 테스트")
  @Test
  void writePostSuccessTest() {
    var req = PostFactory.createWritePostRequest();
    var post = PostFactory.createPost();
    var postDto = PostFactory.createPostDto();

    given(postRepository.save(any())).willReturn(post);
    given(postReadService.toDto(post)).willReturn(postDto);

    var writtenPost = postWriteService.write(req);

    then(postRepository).should(times(1)).save(any());
    then(postReadService).should(times(1)).toDto(post);
    Assertions.assertThat(writtenPost).isNotNull();
    Assertions.assertThat(writtenPost).isEqualTo(postDto);
  }
}