package com.pellto.youtoy.domain.video.repository;

import com.pellto.youtoy.domain.video.domain.Video;
import com.pellto.youtoy.domain.video.domain.VideoComment;
import com.pellto.youtoy.domain.video.domain.VideoCommentInterest;
import com.pellto.youtoy.domain.video.util.VideoCommentFactory;
import com.pellto.youtoy.domain.video.util.VideoCommentInterestFactory;
import com.pellto.youtoy.domain.video.util.VideoFactory;
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
class VideoCommentInterestRepositoryTest {

  private static final String TEST_NAME = "VideoCommentInterestRepository";

  @Autowired
  private VideoCommentInterestRepository videoCommentInterestRepository;

  @Autowired
  private VideoCommentRepository contentsRepository;

  @Autowired
  private VideoRepository videoRepository;

  private Video videoSetting() {
    var video = VideoFactory.create();
    return videoRepository.save(video);
  }

  private VideoComment contentsSetting(Video video) {
    var contents = VideoCommentFactory.create(video);
    return contentsRepository.save(contents);
  }

  private VideoComment contentsSetting() {
    var contents = VideoCommentFactory.create();
    return contentsRepository.save(contents);
  }

  private VideoCommentInterest beforeSetting() {
    var video = videoSetting();
    var content = contentsSetting(video);
    return videoCommentInterestRepository.save(VideoCommentInterestFactory.create(content));
  }

  @DisplayName("[" + TEST_NAME + ": save: success] 관심 저장 성공 테스트")
  @Test
  void saveSuccessTest() {
    var contents = contentsSetting();
    var interest = VideoCommentInterestFactory.createBeforeSaved(contents);

    var savedInterest = videoCommentInterestRepository.save(interest);

    Assertions.assertThat(savedInterest).isNotNull();
    Assertions.assertThat(savedInterest.getId()).isNotNull();
    Assertions.assertThat(savedInterest.getClass()).isEqualTo(VideoCommentInterest.class);
  }

  @DisplayName("[" + TEST_NAME + ": findAllByInterestedContents: success] 관심 contents id 조회 성공 테스트")
  @Test
  void findAllByInterestedContentsSuccessTest() {
    var interest = beforeSetting();

    var interestList = videoCommentInterestRepository
        .findAllByInterestedComment(interest.getInterestedComment());

    Assertions.assertThat(interestList).isNotEmpty();
    Assertions.assertThat(interestList.size()).isEqualTo(1);
    Assertions.assertThat(interestList.get(0).getClass()).isEqualTo(VideoCommentInterest.class);
  }

  @DisplayName("[" + TEST_NAME + ": deleteById: success] 관심 삭제 성공 테스트")
  @Test
  void deleteByIdSuccessTest() {
    var interest = beforeSetting();

    var foundInterest = videoCommentInterestRepository
        .findById(interest.getId())
        .orElse(null);
    assert foundInterest != null;

    videoCommentInterestRepository.deleteById(foundInterest.getId());

    var deletedInterest = videoCommentInterestRepository.findById(foundInterest.getId());
    Assertions.assertThat(deletedInterest).isEmpty();
  }
}