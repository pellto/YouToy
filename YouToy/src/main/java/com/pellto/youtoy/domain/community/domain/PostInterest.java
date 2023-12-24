package com.pellto.youtoy.domain.community.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pellto.youtoy.domain.base.Interest;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
import lombok.ToString;

@Entity
@Getter
@ToString
@Table(name = "community_post_interest")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class PostInterest extends Interest {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "community_post_interest_id")
  private Long id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  @JoinColumn(referencedColumnName = "community_post_id", name = "interesting_post_id")
  private CommunityPost interestedPost;
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
  public PostInterest(boolean dislike, LocalDateTime createdAt, Long id,
      CommunityPost interestedPost, UserUUID interestingUserUuid) {
    super(dislike, createdAt);
    this.id = id;
    this.interestedPost = Objects.requireNonNull(interestedPost);
    this.interestingUserUuid = Objects.requireNonNull(interestingUserUuid);
  }

  public void toggleInterest() {
    super.changeDislike(!this.dislike);
  }
}
