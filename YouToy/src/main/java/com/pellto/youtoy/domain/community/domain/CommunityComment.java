package com.pellto.youtoy.domain.community.domain;

import com.pellto.youtoy.domain.base.Comment;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class CommunityComment extends Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "community_comment_id")
  private Long id;

  // TODO: change community domain FK
  @Column(name = "community_post_id")
  private Long communityPostId;
  @Embedded
  @AttributeOverrides({
      @AttributeOverride(
          name = "value",
          column = @Column(
              name = "commenter_uuid", nullable = false
          )
      )
  })
  private UserUUID commenterUuid;

  @Builder
  public CommunityComment(
      Long likeCount, String content, boolean modified,
      LocalDateTime createdAt, LocalDateTime modifiedAt, Long id,
      Long communityPostId, UserUUID commenterUuid
  ) {
    super(likeCount, content, modified, createdAt, modifiedAt);
    this.id = id;
    this.communityPostId = Objects.requireNonNull(communityPostId);
    this.commenterUuid = Objects.requireNonNull(commenterUuid);
  }

  @Override
  public void increaseLikeCount() {
    super.increaseLikeCount();
  }

  @Override
  public void decreaseLikeCount() {
    super.decreaseLikeCount();
  }

  @Override
  public String changeContent(String s) {
    return super.changeContent(s);
  }
}
