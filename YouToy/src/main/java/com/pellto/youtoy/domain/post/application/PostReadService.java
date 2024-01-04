package com.pellto.youtoy.domain.post.application;

import com.pellto.youtoy.domain.base.application.ReadService;
import com.pellto.youtoy.domain.post.domain.Post;
import com.pellto.youtoy.domain.post.dto.PostDto;
import com.pellto.youtoy.domain.post.repository.PostRepository;
import com.pellto.youtoy.global.exception.NotExistPostException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostReadService implements ReadService<Post, PostDto> {

  private final PostRepository postRepository;

  @Override
  public List<PostDto> findAll() {
    return postRepository.findAll().stream().map(this::toDto).toList();
  }

  @Override
  public PostDto findById(Long id) {
    return toDto(postRepository.findById(id)
        .orElseThrow(NotExistPostException::new)
    );
  }

  @Override
  public Post getById(Long id) {
    return postRepository.getReferenceById(id);
  }

  @Override
  public PostDto toDto(Post post) {
    return new PostDto(
        post.getId(),
        post.getChannelId(),
        post.getContent(),
        post.isModified(),
        post.getComments().size(),
        post.getLikeCount(),
        post.getCreatedAt()
    );
  }
}
