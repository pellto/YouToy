package com.pellto.youtoy.domain.video.domain;

import com.pellto.youtoy.domain.base.ReplyComment;
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
@Table(name = "video_reply_comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class VideoReplyComment extends ReplyComment<VideoComment> {

  @Builder
  public VideoReplyComment(VideoComment parentComment,
      UserUUID commenterUuid, Long id, Long likeCount,
      String content, boolean modified, LocalDateTime createdAt,
      LocalDateTime modifiedAt, boolean mentioned) {
    super(parentComment, commenterUuid, id, likeCount, content, modified, createdAt, modifiedAt,
        mentioned);
  }
}
