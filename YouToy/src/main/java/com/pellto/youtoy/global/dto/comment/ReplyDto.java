package com.pellto.youtoy.global.dto.comment;

import com.pellto.youtoy.comment.domain.model.CommenterInfo;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record ReplyDto(
    Long id,
    Long parentCommentId,
    LocalDateTime createdAt,
    CommenterInfo replierInfo,
    String content,
    Long likeCount,
    boolean ownerLike,
    LocalDateTime updatedAt
) {

}
