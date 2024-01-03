package com.pellto.youtoy.domain.video.repository;

import com.pellto.youtoy.domain.video.domain.Video;
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
class VideoRepositoryTest {

  @Autowired
  private VideoRepository videoRepository;

  @DisplayName("[videoRepository: save: success] 비디오 저장 성공 테스트")
  @Test
  void saveSuccessTest() {
    var video = VideoFactory.createBeforeSaved();

    var savedVideo = videoRepository.save(video);

    Assertions.assertThat(savedVideo).isNotNull();
    Assertions.assertThat(savedVideo.getId()).isNotNull();
    Assertions.assertThat(savedVideo.getChannelId()).isEqualTo(video.getChannelId());
    Assertions.assertThat(savedVideo.getTitle()).isEqualTo(video.getTitle());
    Assertions.assertThat(savedVideo.getDescription()).isEqualTo(video.getDescription());
  }

  @DisplayName("[videoRepository: findAll: success] 비디오 전체 조회 성공 테스트")
  @Test
  void findAllSuccessTest() {
    var video = VideoFactory.createBeforeSaved();
    videoRepository.save(video);

    var foundVideos = videoRepository.findAll();

    Assertions.assertThat(foundVideos).isNotEmpty();
    Assertions.assertThat(foundVideos.size()).isEqualTo(1);
    Assertions.assertThat(foundVideos.get(0).getClass()).isEqualTo(Video.class);
  }

  @DisplayName("[videoRepository: findById: success] 비디오 id 조건 조회 성공 테스트")
  @Test
  void findByIdSuccessTest() {
    var video = VideoFactory.createBeforeSaved();
    videoRepository.save(video);

    var nullableVideo = videoRepository.findById(video.getId());

    Assertions.assertThat(nullableVideo).isNotNull();
    Assertions.assertThat(nullableVideo).isNotEmpty();
    var foundVideo = nullableVideo.get();
    Assertions.assertThat(foundVideo.getChannelId()).isEqualTo(video.getChannelId());
    Assertions.assertThat(foundVideo.getTitle()).isEqualTo(video.getTitle());
    Assertions.assertThat(foundVideo.getDescription()).isEqualTo(video.getDescription());
  }

  @DisplayName("[videoRepository: deleteById: success] 비디오 id 조건 삭제 성공 테스트")
  @Test
  void deleteByIdSuccessTest() {
    var video = VideoFactory.createBeforeSaved();
    videoRepository.save(video);

    videoRepository.deleteById(video.getId());

    var deletedVideo = videoRepository.findById(video.getId());

    Assertions.assertThat(deletedVideo).isEmpty();
  }
}