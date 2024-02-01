package com.pellto.youtoy.comment.application.adapter.out.persistence;

import com.pellto.youtoy.comment.domain.model.Comment;
import com.pellto.youtoy.comment.util.CommentFixtureFactory;
import java.util.ArrayList;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import({CommentPersistenceAdapter.class, CommentMapper.class})
@Tag("persistenceAdapter")
class CommentPersistenceAdapterTest {

  private static final String ADAPTER_NAME = "CommentPersistenceAdapter";

  @Autowired
  private CommentPersistenceAdapter commentPersistenceAdapter;

  @DisplayName("[" + ADAPTER_NAME + "/save] 저장 성공 테스트")
  @Test
  void saveSuccessTest() {
    var beforeSaved = CommentFixtureFactory.createBeforeSaved();
    var comment = CommentFixtureFactory.create(beforeSaved);

    var savedComment = commentPersistenceAdapter.save(beforeSaved);

    Assertions.assertThat(savedComment).isNotNull();
    Assertions.assertThat(savedComment.getId()).isNotNull();
    Assertions.assertThat(savedComment.getCreatedAt()).isNotNull();
    Assertions.assertThat(savedComment.getUpdatedAt()).isNotNull();
    Assertions.assertThat(savedComment.getUpdatedAt()).isEqualTo(savedComment.getCreatedAt());
    Assertions.assertThat(savedComment).usingRecursiveComparison()
        .ignoringFields("id", "createdAt", "updatedAt").isEqualTo(comment);
  }

  @DisplayName("[" + ADAPTER_NAME + "/load] 읽기 성공 테스트")
  @Test
  void loadSuccessTest() {
    var beforeSaved = CommentFixtureFactory.createBeforeSaved();
    var comment = commentPersistenceAdapter.save(beforeSaved);

    var loadedComment = commentPersistenceAdapter.load(comment.getId());

    Assertions.assertThat(loadedComment).isNotNull();
    Assertions.assertThat(loadedComment.getId()).isNotNull();
    Assertions.assertThat(loadedComment.getCreatedAt()).isNotNull();
    Assertions.assertThat(loadedComment).usingRecursiveComparison().isEqualTo(comment);
  }

  @DisplayName("[" + ADAPTER_NAME + "/update] 변경 성공 테스트")
  @Test
  void updateSuccessTest() {
    var beforeSaved = CommentFixtureFactory.createBeforeSaved();
    var savedComment = commentPersistenceAdapter.save(beforeSaved);
    var beforeComment = commentPersistenceAdapter.load(savedComment.getId());
    var beforeUpdatedAt = beforeComment.getUpdatedAt();
    var beforeContent = beforeComment.getContent();
    var changedContent = "NewContent";
    beforeComment.changeContent(changedContent);

    commentPersistenceAdapter.update(beforeComment);

    var changedComment = commentPersistenceAdapter.load(beforeComment.getId());

    Assertions.assertThat(changedComment).isNotNull();
    Assertions.assertThat(changedComment.getId()).isNotNull();
    Assertions.assertThat(changedComment.getCreatedAt()).isNotNull();
    Assertions.assertThat(changedComment).usingRecursiveComparison()
        .ignoringFields("content", "updatedAt")
        .isEqualTo(savedComment);
    Assertions.assertThat(changedComment.getContent())
        .isEqualTo(changedContent);
    Assertions.assertThat(changedComment.getContent())
        .isNotEqualTo(beforeContent);
    Assertions.assertThat(changedComment.getUpdatedAt()).isNotEqualTo(beforeUpdatedAt);
  }

  @DisplayName("[" + ADAPTER_NAME
      + "/loadAllByContentsTypeAndContentsId] contentsType and contentsId 조건 조회 성공 테스트")
  @Test
  void loadAllByContentsTypeAndContentsIdSuccessTest() {
    var beforeSaved = CommentFixtureFactory.createBeforeSaved();
    var savedComment1 = commentPersistenceAdapter.save(beforeSaved);
    var savedComment2 = commentPersistenceAdapter.save(beforeSaved);
    var savedComments = new ArrayList<Comment>();
    savedComments.add(savedComment1);
    savedComments.add(savedComment2);

    var loadedComments = commentPersistenceAdapter.loadAllByContentsTypeAndContentsId(
        savedComment1.getCommentContentsType().getType(), savedComment1.getContentsId());

    Assertions.assertThat(loadedComments).usingRecursiveComparison().isEqualTo(savedComments);
  }

  @DisplayName("[" + ADAPTER_NAME
      + "/deleteAllByContentsIdAndContentsType] contentsType and contentsId 조건 삭제 성공 테스트")
  @Test
  void deleteAllByContentsIdAndContentsTypeSuccessTest() {
    var beforeSaved = CommentFixtureFactory.createBeforeSaved();
    var saved1 = commentPersistenceAdapter.save(beforeSaved);
    var saved2 = commentPersistenceAdapter.save(beforeSaved);

    commentPersistenceAdapter.deleteAllByContentsIdAndContentsType(beforeSaved.getContentsId(),
        beforeSaved.getCommentContentsType());

    Assertions.assertThatThrownBy(() -> commentPersistenceAdapter.load(saved1.getId()))
        .isInstanceOf(IllegalArgumentException.class).hasMessage("댓글 없음");
    Assertions.assertThatThrownBy(() -> commentPersistenceAdapter.load(saved2.getId()))
        .isInstanceOf(IllegalArgumentException.class).hasMessage("댓글 없음");
  }
}