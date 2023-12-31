package com.pellto.youtoy.domain.view.entity;

import com.pellto.youtoy.util.GenerateRandomString;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
public class Video {
    private final Long id;
    private final Long channelId;
    private String title;
    private Long viewCount;
    private Long likeCount;
    private String description;
    private final LocalDateTime createdAt;

    @Builder
    public Video(
            Long id,
            Long channelId,
            String title,
            Long viewCount,
            String description,
            LocalDateTime createdAt,
            Long likeCount
    ) {
        this.id = id;
        this.channelId = Objects.requireNonNull(channelId);
        this.title = title == null ? makeRandomTitle() : title;
        this.viewCount = viewCount == null ? 0 : viewCount;
        this.likeCount = likeCount;
        this.description = description == null ? "" : description;
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }

    private String makeRandomTitle() {
        String prefix = "title-";
        String title = GenerateRandomString.make();
        return prefix + title;
    }

    public void increaseViewCount() {
        this.viewCount += 1;
    }

    public void increaseLikeCount() {
        this.likeCount += 1;
    }

    public void decreaseLikeCount() {
        this.likeCount -= 1;
    }
}
