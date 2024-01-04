package com.pellto.youtoy.domain.post.domain;

import com.pellto.youtoy.domain.base.Reply;
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
@Table(name = "community_post_reply")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class PostReply extends Reply<PostComment> {

  @OneToMany(mappedBy = "interestedReply", cascade = CascadeType.REMOVE)
  private final List<PostReplyInterest> replyInterests = new ArrayList<>();

  @Builder
  public PostReply(PostComment parentComment, UserUUID commenterUuid, Long id, String content,
      boolean modified, LocalDateTime createdAt, LocalDateTime modifiedAt, boolean mentioned) {
    super(parentComment, commenterUuid, id, content, modified, createdAt, modifiedAt, mentioned);
  }

  @Override
  public void changeCommentContent(String s) {
    super.changeCommentContent(s);
  }

  public int getLikeCount() {
    return (int) this.replyInterests.stream().filter((interest) -> !interest.isDislike()).count();
  }
}
