package com.pellto.youtoy.domain.post.domain;

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
@Table(name = "post_comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class PostComment extends Comment<Post> {

  @OneToMany(mappedBy = "parentComment", cascade = CascadeType.REMOVE)
  private final List<PostReply> replies = new ArrayList<>();
  @OneToMany(mappedBy = "interestedComment", cascade = CascadeType.REMOVE)
  private final List<PostCommentInterest> commentInterests = new ArrayList<>();

  @Builder
  public PostComment(Post contents, UserUUID commenterUuid, Long id, String commentContent,
      boolean modified, LocalDateTime createdAt, LocalDateTime modifiedAt) {
    super(contents, commenterUuid, id, commentContent, modified, createdAt, modifiedAt);
  }

  public int getReplyCount() {
    return replies.size();
  }

  @Override
  public void changeCommentContent(String s) {
    super.changeCommentContent(s);
  }

  public int getLikeCount() {
    return (int) this.commentInterests.stream().filter((interest) -> !interest.isDislike()).count();
  }
}
