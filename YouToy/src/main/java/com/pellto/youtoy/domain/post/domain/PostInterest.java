package com.pellto.youtoy.domain.post.domain;

import com.pellto.youtoy.domain.base.ContentsInterest;
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
@Table(name = "post_interest")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class PostInterest extends ContentsInterest<Post> {

  @Builder
  public PostInterest(Post interestedContents, UserUUID interestingUserUuid, Long id,
      boolean dislike,
      LocalDateTime createdAt) {
    super(interestedContents, interestingUserUuid, id, dislike, createdAt);
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
