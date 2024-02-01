package com.pellto.youtoy.comment.application.adapter.out.persistence.reply;

import com.pellto.youtoy.comment.domain.model.Reply;
import com.pellto.youtoy.comment.util.ReplyFixtureFactory;
import java.util.ArrayList;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import({ReplyPersistenceAdapter.class, ReplyMapper.class})
@Tag("persistenceAdapter")
class ReplyPersistenceAdapterTest {

  private static final String ADAPTER_NAME = "ReplyPersistenceAdapter";

  @Autowired
  private ReplyPersistenceAdapter replyPersistenceAdapter;

  private Reply preSetting() {
    return replyPersistenceAdapter.save(ReplyFixtureFactory.createBeforeSaved());
  }

  @DisplayName("[" + ADAPTER_NAME + "/save] 저장 성공 테스트")
  @Test
  void saveSuccessTest() {
    var beforeSaved = ReplyFixtureFactory.createBeforeSaved();
    var reply = ReplyFixtureFactory.create();

    var savedReply = replyPersistenceAdapter.save(beforeSaved);

    Assertions.assertThat(savedReply).isNotNull();
    Assertions.assertThat(savedReply.getId()).isNotNull();
    Assertions.assertThat(savedReply.getCreatedAt()).isNotNull();
    Assertions.assertThat(savedReply.getUpdatedAt()).isNotNull();
    Assertions.assertThat(savedReply.getUpdatedAt()).isEqualTo(savedReply.getCreatedAt());
    Assertions.assertThat(savedReply).usingRecursiveComparison()
        .ignoringFields("id", "createdAt", "updatedAt").isEqualTo(reply);
  }

  @DisplayName("[" + ADAPTER_NAME + "/load] id 조건 조회 성공 테스트")
  @Test
  void loadSuccessTest() {
    var saved = preSetting();

    var loadedReply = replyPersistenceAdapter.load(saved.getId());

    Assertions.assertThat(loadedReply).usingRecursiveComparison().isEqualTo(saved);
  }

  @DisplayName("[" + ADAPTER_NAME + "/load] id 조건 조회 실패 테스트")
  @Test
  void loadFailWithNotExistReplyTest() {
    Assertions.assertThatThrownBy(() -> replyPersistenceAdapter.load(-1L))
        .isInstanceOf(IllegalArgumentException.class).hasMessage("reply 없음");
  }

  @DisplayName("[" + ADAPTER_NAME + "/deleteById] id 조건 삭제 성공 테스트")
  @Test
  void deleteByIdSuccessTest() {
    var saved = preSetting();

    replyPersistenceAdapter.deleteById(saved.getId());

    Assertions.assertThatThrownBy(() -> replyPersistenceAdapter.load(saved.getId()))
        .isInstanceOf(IllegalArgumentException.class).hasMessage("reply 없음");
  }

  @DisplayName("[" + ADAPTER_NAME + "/update] 변경 성공 테스트")
  @Test
  void updateSuccessTest() {
    var saved = preSetting();
    var newContent = "new reply content";
    saved.changeContent(newContent);
    replyPersistenceAdapter.update(saved);

    var updatedReply = replyPersistenceAdapter.load(saved.getId());

    Assertions.assertThat(updatedReply).isNotNull();
    Assertions.assertThat(updatedReply.getId()).isNotNull();
    Assertions.assertThat(updatedReply.getCreatedAt()).isNotNull();
    Assertions.assertThat(updatedReply.getUpdatedAt()).isNotNull();
    Assertions.assertThat(updatedReply.getUpdatedAt()).isNotEqualTo(updatedReply.getCreatedAt());
    Assertions.assertThat(updatedReply).usingRecursiveComparison().isEqualTo(saved);
    Assertions.assertThat(updatedReply.getContent()).isEqualTo(newContent);
  }

  @DisplayName("[" + ADAPTER_NAME + "/loadAllByParentCommentId] parentId 조건 전체 조회 성공 테스트")
  @Test
  void loadAllByParentCommentIdSuccessTest() {
    var saved1 = preSetting();
    var saved2 = preSetting();
    var replies = new ArrayList<Reply>();
    replies.add(saved1);
    replies.add(saved2);

    var loadedReplies = replyPersistenceAdapter.loadAllByParentCommentId(
        saved1.getParentCommentId());

    Assertions.assertThat(loadedReplies.size()).isEqualTo(replies.size());
    Assertions.assertThat(loadedReplies).usingRecursiveComparison().isEqualTo(replies);
  }

  @DisplayName("[" + ADAPTER_NAME + "/loadAllIdsByParentCommentId] parentId 조건 전체 id 조회 성공 테스트")
  @Test
  void loadAllIdsByParentCommentIdSuccessTest() {
    var saved1 = preSetting();
    var saved2 = preSetting();
    var replyIds = new ArrayList<Long>();
    replyIds.add(saved1.getId());
    replyIds.add(saved2.getId());

    var loadedReplyIds = replyPersistenceAdapter.loadAllIdsByParentCommentId(
        saved1.getParentCommentId());

    Assertions.assertThat(loadedReplyIds.size()).isEqualTo(replyIds.size());
    Assertions.assertThat(loadedReplyIds).usingRecursiveComparison().isEqualTo(replyIds);
  }
}