package com.pellto.youtoy.domain.post.dto;

import java.time.LocalDateTime;

public record PostReplyDto(
    Long id,
    Long parentCommentId,
    String commenterUuid,
    int likeCount,
    String commentContent,
    boolean modified,
    LocalDateTime createdAt
) {

}
