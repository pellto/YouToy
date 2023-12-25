package com.pellto.youtoy.domain.community.dto;

import java.time.LocalDateTime;

public record PostReplyCommentDto(
    Long id,
    Long parentCommentId,
    String commenterUuid,
    Long likeCount,
    String content,
    boolean modified,
    LocalDateTime createdAt
) {

}
