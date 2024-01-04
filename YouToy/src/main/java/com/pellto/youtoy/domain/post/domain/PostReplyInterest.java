package com.pellto.youtoy.domain.post.domain;

import com.pellto.youtoy.domain.base.ReplyInterest;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "community_reply_comment_interest")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class PostReplyInterest extends ReplyInterest<PostReply> {

  @Builder
  public PostReplyInterest(PostReply interestedReply, UserUUID interestingUserUuid, Long id,
      boolean dislike, LocalDateTime createdAt) {
    super(interestedReply, interestingUserUuid, id, dislike, createdAt);
  }

  @Override
  public void changeDislike() {
    super.changeDislike();
  }

  @Override
  public void changeCheck(boolean b) {
    super.changeCheck(b);
  }
}
