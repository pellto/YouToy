package com.pellto.youtoy.domain.video.domain;

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
@Table(name = "video")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Video extends Contents {

  @Column(name = "channel_id")
  private Long channelId;
  @Column(name = "title")
  private String title;
  @Column(name = "view_count")
  private Long viewCount;
  @Column(name = "description")
  private String description;
  @Column(name = "created_at")
  private LocalDateTime createdAt;
  @Column(name = "modified_at")
  private LocalDateTime modifiedAt;

  @OneToMany(mappedBy = "contents", cascade = CascadeType.REMOVE)
  private final List<VideoComment> comments = new ArrayList<>();
  @OneToMany(mappedBy = "interestedContents", cascade = CascadeType.REMOVE)
  private final List<VideoInterest> interests = new ArrayList<>();

  @Builder
  public Video(Long id, Long channelId, String title, Long viewCount, String description,
      LocalDateTime createdAt, LocalDateTime modifiedAt) {
    super(id);
    this.channelId = Objects.requireNonNull(channelId);
    this.title = Objects.requireNonNull(title);
    this.viewCount = General.setNullInput(viewCount, 0L);
    this.description = Objects.requireNonNull(description);
    this.createdAt = Temporal.createdAt(createdAt);
    this.modifiedAt = General.setNullInput(modifiedAt, this.createdAt);
  }

  public int getCommentCount() {
    return this.comments.size();
  }

  public int getLikeCount() {
    return (int) this.interests.stream().filter((interest) -> !interest.isDislike()).count();
  }
}
