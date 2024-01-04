package com.pellto.youtoy.domain.video.repository;

import com.pellto.youtoy.domain.video.domain.Video;
import com.pellto.youtoy.domain.video.domain.VideoComment;
import com.pellto.youtoy.domain.video.domain.VideoReply;
import com.pellto.youtoy.domain.video.domain.VideoReplyInterest;
import com.pellto.youtoy.domain.video.util.VideoCommentFactory;
import com.pellto.youtoy.domain.video.util.VideoFactory;
import com.pellto.youtoy.domain.video.util.VideoReplyFactory;
import com.pellto.youtoy.domain.video.util.VideoReplyInterestFactory;
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
class VideoReplyInterestRepositoryTest {

  private static final String TEST_NAME = "VideoReplyInterestRepository";

  @Autowired
  private VideoReplyInterestRepository videoReplyInterestRepository;

  @Autowired
  private VideoReplyRepository contentsRepository;

  @Autowired
  private VideoRepository videoRepository;

  @Autowired
  private VideoCommentRepository videoCommentRepository;

  private Video videoSetting() {
    var video = VideoFactory.create();
    return videoRepository.save(video);
  }

  private VideoComment commentSetting(Video video) {
    var comment = VideoCommentFactory.create(video);
    return videoCommentRepository.save(comment);
  }

  private VideoReply contentsSetting() {
    var contents = VideoReplyFactory.create();
    return contentsRepository.save(contents);
  }

  private VideoReply contentsSetting(VideoComment comment) {
    var contents = VideoReplyFactory.create(comment);
    return contentsRepository.save(contents);
  }

  private VideoReplyInterest beforeSetting() {
    var video = videoSetting();
    var parentComment = commentSetting(video);
    var content = contentsSetting(parentComment);
    return videoReplyInterestRepository.save(VideoReplyInterestFactory.create(content));
  }

  @DisplayName("[" + TEST_NAME + ": save: success] 관심 저장 성공 테스트")
  @Test
  void saveSuccessTest() {
    var contents = contentsSetting();
    var interest = VideoReplyInterestFactory.createBeforeSaved(contents);

    var savedInterest = videoReplyInterestRepository.save(interest);

    Assertions.assertThat(savedInterest).isNotNull();
    Assertions.assertThat(savedInterest.getId()).isNotNull();
    Assertions.assertThat(savedInterest.getClass()).isEqualTo(VideoReplyInterest.class);
  }

  @DisplayName("[" + TEST_NAME + ": findAllByInterestedContents: success] 관심 contents id 조회 성공 테스트")
  @Test
  void findAllByInterestedContentsSuccessTest() {
    var interest = beforeSetting();

    var interestList = videoReplyInterestRepository
        .findAllByInterestedReply(interest.getInterestedReply());

    Assertions.assertThat(interestList).isNotEmpty();
    Assertions.assertThat(interestList.size()).isEqualTo(1);
    Assertions.assertThat(interestList.get(0).getClass())
        .isEqualTo(VideoReplyInterest.class);
  }

  @DisplayName("[" + TEST_NAME + ": deleteById: success] 관심 삭제 성공 테스트")
  @Test
  void deleteByIdSuccessTest() {
    var interest = beforeSetting();

    var foundInterest = videoReplyInterestRepository
        .findById(interest.getId())
        .orElse(null);
    assert foundInterest != null;

    videoReplyInterestRepository.deleteById(foundInterest.getId());

    var deletedInterest = videoReplyInterestRepository.findById(foundInterest.getId());
    Assertions.assertThat(deletedInterest).isEmpty();
  }
}