package com.pellto.youtoy.domain.video.dto;

import java.time.LocalDateTime;

public record VideoReplyDto(
    Long id,
    Long parentCommentId,
    String commenterUuid,
    int likeCount,
    String commentContent,
    boolean modified,
    LocalDateTime createdAt
) {

}
