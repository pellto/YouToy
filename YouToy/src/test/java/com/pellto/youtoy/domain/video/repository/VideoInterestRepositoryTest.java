package com.pellto.youtoy.domain.video.repository;

import com.pellto.youtoy.domain.video.domain.Video;
import com.pellto.youtoy.domain.video.domain.VideoInterest;
import com.pellto.youtoy.domain.video.util.VideoFactory;
import com.pellto.youtoy.domain.video.util.VideoInterestFactory;
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
class VideoInterestRepositoryTest {

  private static final String TEST_NAME = "VideoInterestRepository";

  @Autowired
  private VideoInterestRepository videoInterestRepository;

  @Autowired
  private VideoRepository contentsRepository;

  private Video contentsSetting() {
    var contents = VideoFactory.create();
    return contentsRepository.save(contents);
  }


  private VideoInterest beforeSetting() {
    var video = contentsSetting();
    return videoInterestRepository.save(VideoInterestFactory.create(video));
  }

  @DisplayName("[" + TEST_NAME + ": save: success] 관심 저장 성공 테스트")
  @Test
  void saveSuccessTest() {
    var contents = contentsSetting();
    var interest = VideoInterestFactory.createBeforeSaved(contents);

    var savedInterest = videoInterestRepository.save(interest);

    Assertions.assertThat(savedInterest).isNotNull();
    Assertions.assertThat(savedInterest.getId()).isNotNull();
    Assertions.assertThat(savedInterest.getClass()).isEqualTo(VideoInterest.class);
  }

  @DisplayName("[" + TEST_NAME + ": findAllByInterestedContents: success] 관심 contents id 조회 성공 테스트")
  @Test
  void findAllByInterestedContentsSuccessTest() {
    var interest = beforeSetting();

    var interestList = videoInterestRepository
        .findAllByInterestedContents(interest.getInterestedContents());

    Assertions.assertThat(interestList).isNotEmpty();
    Assertions.assertThat(interestList.size()).isEqualTo(1);
    Assertions.assertThat(interestList.get(0).getClass()).isEqualTo(VideoInterest.class);
  }

  @DisplayName("[" + TEST_NAME + ": deleteById: success] 관심 삭제 성공 테스트")
  @Test
  void deleteByIdSuccessTest() {
    var interest = beforeSetting();

    var foundInterest = videoInterestRepository
        .findById(interest.getId())
        .orElse(null);
    assert foundInterest != null;

    videoInterestRepository.deleteById(foundInterest.getId());

    var deletedInterest = videoInterestRepository.findById(foundInterest.getId());
    Assertions.assertThat(deletedInterest).isEmpty();
  }
}