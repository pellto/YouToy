package com.pellto.youtoy.domain.video.dto;

import java.time.LocalDateTime;

public record VideoCommentDto(
    Long id,
    Long contentsId,
    String commenterUuid,
    int likeCount,
    String commentContent,
    int replyCount,
    boolean modified,
    LocalDateTime createdAt
) {

}
