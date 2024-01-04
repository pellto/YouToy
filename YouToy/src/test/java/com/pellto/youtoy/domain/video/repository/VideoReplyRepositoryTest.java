package com.pellto.youtoy.domain.video.repository;

import com.pellto.youtoy.domain.video.domain.Video;
import com.pellto.youtoy.domain.video.domain.VideoComment;
import com.pellto.youtoy.domain.video.domain.VideoReply;
import com.pellto.youtoy.domain.video.util.VideoCommentFactory;
import com.pellto.youtoy.domain.video.util.VideoFactory;
import com.pellto.youtoy.domain.video.util.VideoReplyFactory;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Tag("repository")
@ExtendWith(SpringExtension.class)
@DataJpaTest
class VideoReplyRepositoryTest {

  private static final String TEST_NAME = "VideoReplyCommentRepository";

  @Autowired
  private VideoReplyRepository videoReplyRepository;

  @Autowired
  private VideoRepository videoRepository;

  @Autowired
  private VideoCommentRepository videoCommentRepository;

  @Autowired
  private EntityManager entityManager;

  private Video videoSetting() {
    var video = VideoFactory.createBeforeSaved();
    return videoRepository.save(video);
  }

  private VideoComment parentCommentSetting(Video video) {
    var comment = VideoCommentFactory.createBeforeSaved(video);
    return videoCommentRepository.save(comment);
  }

  private VideoComment beforeSetting() {
    var video = videoSetting();
    return parentCommentSetting(video);
  }

  @DisplayName("[" + TEST_NAME + ": save: success] 답글 삭제 성공 테스트")
  @Test
  void saveSuccessTest() {
    var parentComment = beforeSetting();
    var reply = VideoReplyFactory.createBeforeSaved(parentComment);

    var savedReply = videoReplyRepository.save(reply);

    Assertions.assertThat(savedReply).isNotNull();
    Assertions.assertThat(savedReply.getId()).isNotNull();
    Assertions.assertThat(savedReply.getCommentContent()).isEqualTo(reply.getCommentContent());
    Assertions.assertThat(savedReply.getCommenterUuid().getValue())
        .isEqualTo(reply.getCommenterUuid().getValue());
    Assertions.assertThat(savedReply.getParentComment().getId())
        .isEqualTo(reply.getParentComment().getId());
  }

  @DisplayName("[" + TEST_NAME + ": findAllByParent: success] 답글 부모 조건 전체 조회 성공 테스트")
  @Test
  void findAllByParentSuccessTest() {
    var parentComment = beforeSetting();
    var reply = VideoReplyFactory.createBeforeSaved(parentComment);
    videoReplyRepository.save(reply);

    var foundReplies = videoReplyRepository.findAllByParentComment(parentComment);

    Assertions.assertThat(foundReplies).isNotEmpty();
    Assertions.assertThat(foundReplies.size()).isEqualTo(1);
    Assertions.assertThat(foundReplies.get(0).getClass()).isEqualTo(VideoReply.class);
    Assertions.assertThat(foundReplies.get(0).getParentComment().getId())
        .isEqualTo(parentComment.getId());
  }

  @DisplayName("[" + TEST_NAME + ": findById: success] 답글 id 조건 조회 성공 테스트")
  @Test
  void findByIdSuccessTest() {
    var parentComment = beforeSetting();
    var reply = VideoReplyFactory.createBeforeSaved(parentComment);
    videoReplyRepository.save(reply);

    var nullableReply = videoReplyRepository.findById(reply.getId());

    Assertions.assertThat(nullableReply).isNotNull();
    Assertions.assertThat(nullableReply).isNotEmpty();
    var foundReply = nullableReply.get();
    Assertions.assertThat(foundReply.getParentComment().getId())
        .isEqualTo(reply.getParentComment().getId());
    Assertions.assertThat(foundReply.getCommenterUuid().getValue())
        .isEqualTo(reply.getCommenterUuid().getValue());
    Assertions.assertThat(foundReply.getCommentContent()).isEqualTo(reply.getCommentContent());
  }

  @DisplayName("[" + TEST_NAME + ": deleteById: success] 답글 id 조건 삭제 성공 테스트")
  @Test
  void deleteByIdSuccessTest() {
    var parentComment = beforeSetting();
    var reply = VideoReplyFactory.createBeforeSaved(parentComment);
    videoReplyRepository.save(reply);
    var nullableReply = videoReplyRepository.findById(reply.getId());
    var replyId = nullableReply.get().getId();

    videoReplyRepository.deleteById(replyId);

    var deletedReply = videoReplyRepository.findById(replyId);

    Assertions.assertThat(deletedReply).isEmpty();
  }

  @DisplayName("[" + TEST_NAME + ": deleteWithParentComment: success] 삭제시 해당 댓글의 모든 답글 삭제 성공 테스트")
  @Test
  void deleteWithParentCommentSuccessTest() {
    var parentComment = beforeSetting();
    var reply = VideoReplyFactory.createBeforeSaved(parentComment);
    videoReplyRepository.save(reply);
    entityManager.flush();
    entityManager.clear();

    videoCommentRepository.deleteById(parentComment.getId());

    var deletedReplies = videoReplyRepository.findAll();

    Assertions.assertThat(deletedReplies).isEmpty();
  }

  @DisplayName("[" + TEST_NAME + ": deleteWithVideo: success] 비디오 삭제시 해당 비디오의 모든 답글 삭제 성공 테스트")
  @Test
  void deleteWithVideoSuccessTest() {
    var parentComment = beforeSetting();
    var reply = VideoReplyFactory.createBeforeSaved(parentComment);
    videoReplyRepository.save(reply);
    entityManager.flush();
    entityManager.clear();

    videoRepository.deleteById(parentComment.getContents().getId());

    var deletedReplies = videoReplyRepository.findAll();

    Assertions.assertThat(deletedReplies).isEmpty();
  }
}