package com.pellto.youtoy.domain.community.application;

import com.pellto.youtoy.domain.community.domain.CommunityPost;
import com.pellto.youtoy.domain.community.dto.CommunityPostDto;
import com.pellto.youtoy.domain.community.dto.WritePostRequest;
import com.pellto.youtoy.domain.community.repository.CommunityPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommunityPostWriteService {

  private final CommunityPostRepository postRepository;
  private final CommentPostReadService postReadService;

  public void deleteById(Long id) {
    postRepository.deleteById(id);
  }

  public CommunityPostDto writePost(WritePostRequest req) {
    var post = CommunityPost.builder()
        .channelId(req.channelId())
        .content(req.content())
        .build();
    return postReadService.toDto(postRepository.save(post));
  }
}
