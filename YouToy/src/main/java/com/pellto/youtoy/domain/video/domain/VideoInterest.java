package com.pellto.youtoy.domain.video.domain;

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
@Table(name = "video_interest")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class VideoInterest extends ContentsInterest<Video> {

  @Builder
  public VideoInterest(Video interestedContents,
      UserUUID interestingUserUuid, Long id, boolean dislike,
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
