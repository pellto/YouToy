package com.pellto.youtoy.domain.comment.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
public class Comment {
    private final Long id;
    private final Long videoId;
    private final Long userId;
    private final Long repliedCommentId;
    private final LocalDateTime createdAt;
    private final boolean video;
    private String content;
    private Long likeCount;

    @Builder
    public Comment(
            Long id,
            Long videoId,
            boolean video,
            Long userId,
            Long repliedCommentId,
            String content,
            LocalDateTime createdAt,
            Long likeCount
    ) {
        this.id = id;
        this.videoId = Objects.requireNonNull(videoId);
        this.video = video;
        this.userId = Objects.requireNonNull(userId);
        this.repliedCommentId = repliedCommentId;
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
        this.content = content == null ? "" : content;
        this.likeCount = likeCount;
    }

    public void increaseLikeCount() {
        this.likeCount += 1;
    }

    public void decreaseLikeCount() {
        this.likeCount -= 1;
    }
}
