package com.pellto.youtoy.domain.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import com.pellto.youtoy.global.util.General;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.FetchType;
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
public abstract class Reply<CM> extends BaseComment {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "reply_comment_id")
  private Long id;

  private boolean mentioned;
  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  @JoinColumn(referencedColumnName = "comment_id", name = "parent_comment_id")
  private CM parentComment;
  @Embedded
  @AttributeOverrides(@AttributeOverride(
      name = "value",
      column = @Column(
          name = "commenter_uuid", nullable = false
      )
  ))
  private UserUUID commenterUuid;

  public Reply(CM parentComment, UserUUID commenterUuid, Long id,
      String content,
      boolean modified, LocalDateTime createdAt,
      LocalDateTime modifiedAt, boolean mentioned) {
    super(content, modified, createdAt, modifiedAt);
    this.id = id;
    this.mentioned = General.setNullInput(mentioned, false);
    this.parentComment = Objects.requireNonNull(parentComment);
    this.commenterUuid = Objects.requireNonNull(commenterUuid);
  }

  @Override
  protected void changeCommentContent(String s) {
    super.changeCommentContent(s);
  }
}
