package com.pellto.youtoy.domain.video.repository;

import com.pellto.youtoy.domain.video.domain.Video;
import com.pellto.youtoy.domain.video.domain.VideoComment;
import com.pellto.youtoy.domain.video.util.VideoCommentFactory;
import com.pellto.youtoy.domain.video.util.VideoFactory;
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
class VideoCommentRepositoryTest {

  @Autowired
  private VideoCommentRepository videoCommentRepository;

  @Autowired
  private VideoRepository videoRepository;

  @Autowired
  private EntityManager entityManager;

  private Video videoSetting() {
    var video = VideoFactory.createBeforeSaved();
    return videoRepository.save(video);
  }

  @DisplayName("[videoCommentRepository: save: success] 비디오 댓글 저장 성공 테스트")
  @Test
  void saveSuccessTest() {
    var video = videoSetting();
    var comment = VideoCommentFactory.createBeforeSaved(video);

    var savedComment = videoCommentRepository.save(comment);

    Assertions.assertThat(savedComment).isNotNull();
    Assertions.assertThat(savedComment.getId()).isNotNull();
    Assertions.assertThat(savedComment.getCommentContent()).isEqualTo(comment.getCommentContent());
    Assertions.assertThat(savedComment.getCommenterUuid().getValue())
        .isEqualTo(comment.getCommenterUuid().getValue());
    Assertions.assertThat(savedComment.getContents().getId())
        .isEqualTo(comment.getContents().getId());
  }

  @DisplayName("[videoCommentRepository: findAll: success] 비디오 댓글 전체 조회 성공 테스트")
  @Test
  void findAllSuccessTest() {
    var video = videoSetting();
    var comment = VideoCommentFactory.createBeforeSaved(video);
    videoCommentRepository.save(comment);

    var foundComments = videoCommentRepository.findAll();

    Assertions.assertThat(foundComments).isNotEmpty();
    Assertions.assertThat(foundComments.size()).isEqualTo(1);
    Assertions.assertThat(foundComments.get(0).getClass()).isEqualTo(VideoComment.class);
  }

  @DisplayName("[videoCommentRepository: findById: success] 비디오 댓글 id 조건 조회 성공 테스트")
  @Test
  void findByIdSuccessTest() {
    var video = videoSetting();
    var comment = VideoCommentFactory.createBeforeSaved(video);
    videoCommentRepository.save(comment);

    var nullableComment = videoCommentRepository.findById(comment.getId());

    Assertions.assertThat(nullableComment).isNotNull();
    Assertions.assertThat(nullableComment).isNotEmpty();
    var foundComment = nullableComment.get();
    Assertions.assertThat(foundComment.getContents().getId())
        .isEqualTo(comment.getContents().getId());
    Assertions.assertThat(foundComment.getCommenterUuid().getValue())
        .isEqualTo(comment.getCommenterUuid().getValue());
    Assertions.assertThat(foundComment.getCommentContent()).isEqualTo(comment.getCommentContent());
  }

  @DisplayName("[videoCommentRepository: deleteById: success] 비디오 댓글 id 조건 삭제 성공 테스트")
  @Test
  void deleteByIdSuccessTest() {
    var video = videoSetting();
    var comment = VideoCommentFactory.createBeforeSaved(video);
    videoCommentRepository.save(comment);
    var nullableComment = videoCommentRepository.findById(comment.getId());
    var commentId = nullableComment.get().getId();

    videoCommentRepository.deleteById(commentId);

    var deletedComment = videoCommentRepository.findById(commentId);

    Assertions.assertThat(deletedComment).isEmpty();
  }

  @DisplayName("[videoCommentRepository: deleteWithVideo: success] 비디오 삭제시 해당 비디오의 모든 댓글 삭제 성공 테스트")
  @Test
  void deleteWithVideoSuccessTest() {
    var video = videoSetting();
    var comment = VideoCommentFactory.createBeforeSaved(video);
    videoCommentRepository.save(comment);
    entityManager.flush();
    entityManager.clear();

    videoRepository.deleteById(video.getId());

    var deletedComment = videoCommentRepository.findAll();

    Assertions.assertThat(deletedComment).isEmpty();
  }
}