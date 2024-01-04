package com.pellto.youtoy.domain.post.domain;

import com.pellto.youtoy.domain.base.Contents;
import com.pellto.youtoy.global.util.General;
import com.pellto.youtoy.global.util.Temporal;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "post")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Post extends Contents {

  @Column(name = "channel_id")
  private final Long channelId;
  @Column(name = "created_at")
  private final LocalDateTime createdAt;
  @Column(name = "content")
  private String content;
  @Column(name = "modified_at")
  private LocalDateTime modifiedAt;

  @OneToMany(mappedBy = "contents", cascade = CascadeType.REMOVE)
  private final List<PostComment> comments = new ArrayList<>();
  @OneToMany(mappedBy = "interestedContents", cascade = CascadeType.REMOVE)
  private final List<PostInterest> interests = new ArrayList<>();

  @Builder
  public Post(Long id, Long channelId, LocalDateTime createdAt, String content,
      LocalDateTime modifiedAt) {
    super(id);
    this.channelId = Objects.requireNonNull(channelId);
    this.createdAt = Temporal.createdAt(createdAt);
    this.content = Objects.requireNonNull(content);
    this.modifiedAt = General.setNullInput(modifiedAt, this.createdAt);
  }

  public boolean isModified() {
    return this.createdAt == this.modifiedAt;
  }

  public int getLikeCount() {
    return (int) this.interests.stream().filter((interest) -> !interest.isDislike()).count();
  }
}
