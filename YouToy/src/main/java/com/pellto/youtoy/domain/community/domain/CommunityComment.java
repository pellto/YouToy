package com.pellto.youtoy.domain.community.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pellto.youtoy.domain.base.Comment;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "community_comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class CommunityComment extends Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "community_comment_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  @JoinColumn(referencedColumnName = "community_post_id", name = "community_post_id")
  private CommunityPost communityPost;
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

  @OneToMany(mappedBy = "parentComment", cascade = CascadeType.REMOVE)
  private final List<PostReplyComment> replies = new ArrayList<>();
  @OneToMany(mappedBy = "interestedCommunityComment", cascade = CascadeType.REMOVE)
  private final List<CommunityCommentInterest> commentInterests = new ArrayList<>();

  @Builder
  public CommunityComment(
      Long likeCount, String content, boolean modified,
      LocalDateTime createdAt, LocalDateTime modifiedAt, Long id,
      CommunityPost communityPost, UserUUID commenterUuid
  ) {
    super(likeCount, content, modified, createdAt, modifiedAt);
    this.id = id;
    this.communityPost = Objects.requireNonNull(communityPost);
    this.commenterUuid = Objects.requireNonNull(commenterUuid);
  }

  @Override
  public String changeContent(String s) {
    return super.changeContent(s);
  }

  public Long getLikeInterestCount() {
    return this.commentInterests
        .stream()
        .filter((interest) -> !interest.isDislike())
        .count();
  }
}
