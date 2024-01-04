package com.pellto.youtoy.domain.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pellto.youtoy.domain.user.domain.UserUUID;
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


@MappedSuperclass
@Getter
@NoArgsConstructor
public abstract class Comment<C> extends BaseComment {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "comment_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonIgnore
  @JoinColumn(referencedColumnName = "contents_id", name = "contents_id")
  private C contents;
  @Embedded
  @AttributeOverrides({
      @AttributeOverride(
          name = "value",
          column = @Column(
              name = "commenter_uuid", nullable = false
          )
      )
  })
  private UserUUID commenterUuid;

  protected Comment(C contents, UserUUID commenterUuid, Long id,
      String commentContent,
      boolean modified,
      LocalDateTime createdAt,
      LocalDateTime modifiedAt) {
    super(commentContent, modified, createdAt, modifiedAt);
    this.id = id;
    this.commenterUuid = Objects.requireNonNull(commenterUuid);
    this.contents = Objects.requireNonNull(contents);
  }

  protected void changeCommentContent(String s) {
    super.changeCommentContent(s);
    this.modified = true;
    this.modifiedAt = LocalDateTime.now();
  }
}
