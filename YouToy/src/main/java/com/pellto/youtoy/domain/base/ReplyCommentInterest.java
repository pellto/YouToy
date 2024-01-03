package com.pellto.youtoy.domain.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@MappedSuperclass
@NoArgsConstructor
public abstract class ReplyCommentInterest<RC> extends
    Interest {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "reply_comment_interest_id")
  private Long id;

  @ManyToOne
  @JsonIgnore
  @JoinColumn(referencedColumnName = "reply_comment_id", name = "interested_reply_comment_id")
  private RC interestedReplyComment;
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

  public ReplyCommentInterest(RC interestedReplyComment, UserUUID interestingUserUuid,
      Long id, boolean dislike, LocalDateTime createdAt) {
    super(dislike, createdAt);
    this.id = id;
    this.interestedReplyComment = Objects.requireNonNull(interestedReplyComment);
    this.interestingUserUuid = Objects.requireNonNull(interestingUserUuid);
  }
}
