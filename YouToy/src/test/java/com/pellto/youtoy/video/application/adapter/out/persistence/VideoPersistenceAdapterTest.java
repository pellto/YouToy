package com.pellto.youtoy.video.application.adapter.out.persistence;


import com.pellto.youtoy.video.util.VideoFixtureFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import({VideoPersistenceAdapter.class, VideoMapper.class})
@Tag("persistenceAdapter")
class VideoPersistenceAdapterTest {

  private static final String ADAPTER_NAME = "VideoPersistenceAdapter";

  @Autowired
  private VideoPersistenceAdapter videoPersistenceAdapter;

  @DisplayName("[" + ADAPTER_NAME + "/save] 저장 성공 테스트")
  @Test
  void saveSuccessTest() {
    var beforeSaved = VideoFixtureFactory.createBeforeSaved();
    var video = VideoFixtureFactory.create();

    var savedVideo = videoPersistenceAdapter.save(beforeSaved);

    Assertions.assertThat(savedVideo).isNotNull();
    Assertions.assertThat(savedVideo.getId()).isNotNull();
    Assertions.assertThat(savedVideo.getCreatedAt()).isNotNull();
    Assertions.assertThat(savedVideo.getUpdatedAt()).isNotNull();
    Assertions.assertThat(savedVideo.getUpdatedAt()).isEqualTo(savedVideo.getCreatedAt());
    Assertions.assertThat(savedVideo).usingRecursiveComparison()
        .ignoringFields("id", "createdAt", "updatedAt", "encodingRequestId", "videoEncodedInfo")
        .isEqualTo(video);
  }

  @DisplayName("[" + ADAPTER_NAME + "/load] 조회 성공 테스트")
  @Test
  void loadSuccessTest() {
    var beforeSaved = VideoFixtureFactory.createBeforeSaved();
    var saved = videoPersistenceAdapter.save(beforeSaved);

    var loadedVideo = videoPersistenceAdapter.load(saved.getId());

    Assertions.assertThat(loadedVideo).isNotNull();
    Assertions.assertThat(loadedVideo.getId()).isNotNull();
    Assertions.assertThat(loadedVideo.getCreatedAt()).isNotNull();
    Assertions.assertThat(loadedVideo).usingRecursiveComparison().isEqualTo(saved);
  }

}