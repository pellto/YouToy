package com.pellto.youtoy.domain.community.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pellto.youtoy.domain.deprecated.base.ReplyCommentInterest;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
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
@Table(name = "community_reply_comment_interest")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class CommunityReplyCommentInterest extends ReplyCommentInterest {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "community_reply_comment_interest_id")
  private Long id;
  @ManyToOne
  @JsonIgnore
  @JoinColumn(referencedColumnName = "post_reply_comment_id", name = "interested_community_reply_comment_id")
  private PostReplyComment interestedReplyComment;
  @Embedded
  @AttributeOverrides({
      @AttributeOverride(
          name = "value",
          column = @Column(
              name = "interesting_user_uuid", nullable = false
          )
      )
  })
  private UserUUID interestingUserUuid;

  @Builder
  public CommunityReplyCommentInterest(boolean dislike, LocalDateTime createdAt, Long id,
      PostReplyComment interestedReplyComment, UserUUID interestingUserUuid) {
    super(dislike, createdAt);
    this.id = id;
    this.interestedReplyComment = Objects.requireNonNull(interestedReplyComment);
    this.interestingUserUuid = Objects.requireNonNull(interestingUserUuid);
  }

  public void toggleInterest() {
    super.changeDislike(!this.dislike);
  }
}
