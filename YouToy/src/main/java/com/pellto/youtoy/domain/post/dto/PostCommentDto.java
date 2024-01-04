package com.pellto.youtoy.domain.post.dto;

import java.time.LocalDateTime;

public record PostCommentDto(
    Long id,
    Long postId,
    String commenterUuid,
    int likeCount,
    String commentContent,
    int replyCount,
    boolean modified,
    LocalDateTime createdAt
) {

}
