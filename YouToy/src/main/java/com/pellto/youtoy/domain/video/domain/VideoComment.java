package com.pellto.youtoy.domain.video.domain;

import com.pellto.youtoy.domain.base.Comment;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "video_comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class VideoComment extends Comment<Video> {

  @OneToMany(mappedBy = "parentComment", cascade = CascadeType.REMOVE)
  private final List<VideoReplyComment> replies = new ArrayList<>();
  @OneToMany(mappedBy = "interestedComment", cascade = CascadeType.REMOVE)
  private final List<VideoCommentInterest> commentInterests = new ArrayList<>();


  @Builder
  public VideoComment(Video contents, UserUUID commenterUuid, Long id,
      Long likeCount, String commentContent, boolean modified, LocalDateTime createdAt,
      LocalDateTime modifiedAt) {
    super(contents, commenterUuid, id, likeCount, commentContent, modified, createdAt, modifiedAt);
  }

  public int getReplyCount() {
    return replies.size();
  }
}
