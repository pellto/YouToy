package com.pellto.youtoy.domain.community.dto;

import java.time.LocalDateTime;

public record CommunityCommentDto(
    Long id,
    Long communityPostId,
    String commenterUuid,
    Long likeCount,
    String content,
    boolean modified,
    LocalDateTime createdAt
) {

}
