package com.pellto.youtoy.domain.community.application;

import com.pellto.youtoy.domain.community.domain.CommunityPost;
import com.pellto.youtoy.domain.community.dto.CommunityPostDto;
import com.pellto.youtoy.domain.community.repository.CommunityPostRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentPostReadService {

  private final CommunityPostRepository postRepository;

  public List<CommunityPostDto> findAll() {
    return postRepository.findAll().stream().map(this::toDto).toList();
  }

  public CommunityPostDto findById(Long id) {
    return toDto(
        postRepository.findById(id).orElseThrow(
            () -> new UnsupportedOperationException("없다")
        )
    );
  }

  public CommunityPost getById(Long id) {
    return postRepository.getReferenceById(id);
  }

  public CommunityPostDto toDto(CommunityPost post) {
    return new CommunityPostDto(
        post.getId(),
        post.getChannelId(),
        post.getContent(),
        post.isModified(),
        post.getComments().size(),
        post.getPostInterests().size(),
        post.getCreatedAt()
    );
  }
}
