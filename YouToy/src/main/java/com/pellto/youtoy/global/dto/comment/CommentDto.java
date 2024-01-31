package com.pellto.youtoy.global.dto.comment;

import com.pellto.youtoy.comment.domain.model.CommenterInfo;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record CommentDto(
    Long id,
    CommenterInfo commenterInfo,
    Long contentsId,
    String commentContentsType,
    String content,
    boolean ownerLike,
    Long replyCount,
    Long likeCount,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

}
