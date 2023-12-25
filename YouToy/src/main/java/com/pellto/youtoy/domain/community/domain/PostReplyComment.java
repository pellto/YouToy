package com.pellto.youtoy.domain.community.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pellto.youtoy.domain.base.ReplyComment;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "community_post_reply")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class PostReplyComment extends ReplyComment {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "post_reply_comment")
  private Long id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  @JoinColumn(referencedColumnName = "community_comment_id", name = "parrent_comment_id")
  private CommunityComment parentComment;

  @Embedded
  @AttributeOverrides(@AttributeOverride(
      name = "value",
      column = @Column(
          name = "commenter_uuid", nullable = false
      )
  ))
  private UserUUID commenterUuid;

  @Builder
  public PostReplyComment(Long likeCount, String content, boolean modified,
      LocalDateTime createdAt, LocalDateTime modifiedAt, boolean mentioned,
      Long id, CommunityComment parentComment, UserUUID commenterUuid
  ) {
    super(likeCount, content, modified, createdAt, modifiedAt, mentioned);
    this.id = id;
    this.parentComment = Objects.requireNonNull(parentComment);
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
