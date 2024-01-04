package com.pellto.youtoy.domain.post.application;

import com.pellto.youtoy.domain.base.application.WriteUpdateDeleteService;
import com.pellto.youtoy.domain.post.domain.Post;
import com.pellto.youtoy.domain.post.dto.PostDto;
import com.pellto.youtoy.domain.post.dto.PostModifyRequest;
import com.pellto.youtoy.domain.post.dto.PostWriteRequest;
import com.pellto.youtoy.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostWriteService implements
    WriteUpdateDeleteService<PostDto, PostWriteRequest, PostModifyRequest> {

  private final PostRepository postRepository;
  private final PostReadService postReadService;

  @Override
  public PostDto write(PostWriteRequest writeRequest) {
    var post = Post.builder()
        .channelId(writeRequest.channelId())
        .content(writeRequest.content())
        .build();
    return postReadService.toDto(postRepository.save(post));
  }

  @Override
  public PostDto modify(PostModifyRequest modifyRequest) {
    return null;
  }

  @Override
  public void deleteById(Long id) {
    postRepository.deleteById(id);
  }
}
