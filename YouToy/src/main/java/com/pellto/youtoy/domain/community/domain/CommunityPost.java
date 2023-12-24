package com.pellto.youtoy.domain.community.domain;

import com.pellto.youtoy.global.util.General;
import com.pellto.youtoy.global.util.Temporal;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "community_post")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class CommunityPost {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "community_post_id")
  private final Long id;
  @Column(name = "channel_id")
  private final Long channelId;
  @Column(name = "created_at")
  private final LocalDateTime createdAt;
  @Column(name = "content")
  private String content;
  @Column(name = "modified_at")
  private LocalDateTime modifiedAt;

  @OneToMany(mappedBy = "communityPost", cascade = CascadeType.REMOVE)
  private final List<CommunityComment> comments = new ArrayList<>();


  @Builder
  public CommunityPost(Long id, Long channelId, LocalDateTime createdAt, String content,
      LocalDateTime modifiedAt) {
    this.id = id;
    this.channelId = Objects.requireNonNull(channelId);
    this.createdAt = Temporal.createdAt(createdAt);
    this.content = Objects.requireNonNull(content);
    this.modifiedAt = General.setNullInput(modifiedAt, this.createdAt);
  }

  public boolean isModified() {
    return this.createdAt == this.modifiedAt;
  }
}
