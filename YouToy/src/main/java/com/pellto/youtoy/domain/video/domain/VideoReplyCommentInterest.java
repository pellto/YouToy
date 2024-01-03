package com.pellto.youtoy.domain.video.domain;

import com.pellto.youtoy.domain.base.ReplyCommentInterest;
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
@Table(name = "video_reply_comment_interest")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class VideoReplyCommentInterest extends ReplyCommentInterest<VideoReplyComment> {

  @Builder
  public VideoReplyCommentInterest(VideoReplyComment interestedReplyComment,
      UserUUID interestingUserUuid, Long id, boolean dislike,
      LocalDateTime createdAt) {
    super(interestedReplyComment, interestingUserUuid, id, dislike, createdAt);
  }
}
