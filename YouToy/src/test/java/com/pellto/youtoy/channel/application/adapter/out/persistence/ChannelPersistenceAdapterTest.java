package com.pellto.youtoy.channel.application.adapter.out.persistence;

import com.pellto.youtoy.channel.domain.model.Channel;
import com.pellto.youtoy.channel.domain.model.ChannelInfo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import({ChannelPersistenceAdapter.class, ChannelMapper.class})
@Tag("persistenceAdapter")
class ChannelPersistenceAdapterTest {

  private static final String ADAPTER_NAME = "ChannelPersistenceAdapter";

  @Autowired
  private ChannelPersistenceAdapter channelPersistenceAdapter;

  @DisplayName("[" + ADAPTER_NAME + "/save] 저장 성공 테스트")
  @Test
  void saveSuccessTest() {
    var info = ChannelInfo.builder()
        .displayName("displayName")
        .description("description")
        .profilePath("profilePath")
        .bannerPath("bannerPath")
        .build();
    var channel = Channel.builder()
        .ownerId(1L)
        .channelInfo(info)
        .build();

    var savedChannel = channelPersistenceAdapter.save(channel);

    Assertions.assertThat(savedChannel).isNotNull();
    Assertions.assertThat(savedChannel.getId()).isNotNull();
    Assertions.assertThat(savedChannel.getCreatedAt()).isNotNull();
    Assertions.assertThat(savedChannel.getChannelInfo()).usingRecursiveComparison().isEqualTo(info);
    Assertions.assertThat(savedChannel.getOwnerId()).isEqualTo(1L);
  }

  @DisplayName("[" + ADAPTER_NAME + "/load] 읽기 성공 테스트")
  @Test
  void loadSuccessTest() {
    var info = ChannelInfo.builder()
        .displayName("displayName")
        .description("description")
        .profilePath("profilePath")
        .bannerPath("bannerPath")
        .build();
    var channel = Channel.builder()
        .ownerId(1L)
        .channelInfo(info)
        .build();
    var savedChannel = channelPersistenceAdapter.save(channel);

    var loadedChannel = channelPersistenceAdapter.load(savedChannel.getId());

    Assertions.assertThat(loadedChannel).isNotNull();
    Assertions.assertThat(loadedChannel).usingRecursiveComparison().isEqualTo(savedChannel);
  }

  @DisplayName("[" + ADAPTER_NAME + "/delete] 삭제 성공 테스트")
  @Test
  void deleteSuccessTest() {
    var info = ChannelInfo.builder()
        .displayName("displayName")
        .description("description")
        .profilePath("profilePath")
        .bannerPath("bannerPath")
        .build();
    var channel = Channel.builder()
        .ownerId(1L)
        .channelInfo(info)
        .build();
    var savedChannel = channelPersistenceAdapter.save(channel);

    channelPersistenceAdapter.delete(savedChannel);

    Assertions.assertThatThrownBy(() -> channelPersistenceAdapter.load(savedChannel.getId()))
        .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("변경 예정");
  }
}