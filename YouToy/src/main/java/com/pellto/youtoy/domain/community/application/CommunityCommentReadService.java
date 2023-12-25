package com.pellto.youtoy.domain.community.application;

import com.pellto.youtoy.domain.community.domain.CommunityComment;
import com.pellto.youtoy.domain.community.dto.CommunityCommentDto;
import com.pellto.youtoy.domain.community.repository.CommunityCommentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommunityCommentReadService {

  private final CommunityCommentRepository communityCommentRepository;

  public List<CommunityCommentDto> findAll() {
    return communityCommentRepository.findAll().stream().map(this::toDto).toList();
  }

  public CommunityCommentDto findById(Long id) {
    var nullableComment = communityCommentRepository.findById(id);
    if (nullableComment.isEmpty()) {
      throw new UnsupportedOperationException("없음");
    }
    return toDto(nullableComment.get());
  }

  public CommunityComment getById(Long id) {
    return communityCommentRepository.getReferenceById(id);
  }

  public CommunityCommentDto toDto(CommunityComment comment) {
    return new CommunityCommentDto(
        comment.getId(),
        comment.getCommunityPost().getId(),
        comment.getCommenterUuid().getValue(),
        comment.getLikeCount(),
        comment.getContent(),
        comment.getReplies().size(),
        comment.isModified(),
        comment.getCreatedAt()
    );
  }
}
