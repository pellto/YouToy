package com.pellto.youtoy.domain.video.dto;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
public class VideoContentsDto {
    private final Long id;
    private final Long channelId;
    private final String title;
    private final Long viewCount;
    private final String description;
    private final LocalDateTime createdAt;
    private final Long likeCount;
    private final boolean isVideo;

    public VideoContentsDto(
            Long id,
            Long channelId,
            String title,
            Long viewCount,
            String description,
            LocalDateTime createdAt,
            Long likeCount,
            boolean isVideo
    ) {
        this.id = Objects.requireNonNull(id);
        this.channelId = Objects.requireNonNull(channelId);
        this.title = Objects.requireNonNull(title);
        this.viewCount = Objects.requireNonNull(viewCount);
        this.description = Objects.requireNonNull(description);
        this.createdAt = Objects.requireNonNull(createdAt);
        this.likeCount = Objects.requireNonNull(likeCount);
        this.isVideo = isVideo;
    }
}
