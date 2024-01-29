package com.pellto.youtoy.post.domain.model;

import com.pellto.youtoy.global.dto.post.PostDto;
import com.pellto.youtoy.global.util.General;
import com.pellto.youtoy.global.util.Temporal;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Post {

  private final Long id;
  private final Long channelId;
  private final LocalDateTime createdAt;
  private PostContent postContent;
  private Long viewCount;
  private Long likeCount;
  private LocalDateTime updatedAt;

  @Builder
  public Post(Long id, Long channelId, LocalDateTime createdAt, PostContent postContent,
      Long viewCount, Long likeCount, LocalDateTime updatedAt) {
    this.id = id;
    this.channelId = Objects.requireNonNull(channelId);
    this.postContent = Objects.requireNonNull(postContent);
    this.createdAt = Temporal.createdAt(createdAt);
    this.viewCount = General.setNullInput(viewCount, 0L);
    this.likeCount = General.setNullInput(likeCount, 0L);
    this.updatedAt = General.setNullInput(updatedAt, createdAt);
  }

  public void increaseLikeCount() {
    this.likeCount += 1;
  }

  public void decreaseLikeCount() {
    this.likeCount -= 1;
  }

  public void increaseViewCount() {
    this.viewCount += 1;
  }

  public void changeTitle(String title) {
    this.postContent = PostContent.builder()
        .content(this.postContent.getContent())
        .title(title)
        .build();
    this.updatedAt = LocalDateTime.now();
  }

  public void changeContent(String content) {
    this.postContent = PostContent.builder()
        .content(content)
        .title(this.postContent.getTitle())
        .build();
    this.updatedAt = LocalDateTime.now();
  }

  public void changePost(PostContent post) {
    this.postContent = post;
    this.updatedAt = LocalDateTime.now();
  }

  public PostDto toDto() {
    if (id == null) {
      throw new IllegalArgumentException("ID는 필수 입니다.");
    }
    return PostDto.builder()
        .id(id)
        .channelId(channelId)
        .content(postContent.getContent())
        .title(postContent.getTitle())
        .createdAt(createdAt)
        .updatedAt(updatedAt)
        .viewCount(viewCount)
        .likeCount(likeCount)
        .build();
  }
}
