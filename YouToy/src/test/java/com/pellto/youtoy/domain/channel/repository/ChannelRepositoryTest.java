package com.pellto.youtoy.domain.channel.repository;

import static com.pellto.youtoy.domain.channel.util.ChannelFactory.createBeforeSavedChannel;

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
class ChannelRepositoryTest {

  @Autowired
  private ChannelRepository channelRepository;

  @DisplayName("[channelRepository: save: success] 저장 성공 테스트")
  @Test
  void saveSuccessTest() {
    var channel = createBeforeSavedChannel();

    var savedChannel = channelRepository.save(channel);

    Assertions.assertThat(savedChannel.getId()).isNotNull();
    Assertions.assertThat(savedChannel.getHandle()).isEqualTo(channel.getHandle());
    Assertions.assertThat(savedChannel.getDisplayName()).isEqualTo(channel.getDisplayName());
    Assertions.assertThat(savedChannel.getBanner()).isEqualTo(channel.getBanner());
    Assertions.assertThat(savedChannel.getProfile()).isEqualTo(channel.getProfile());
  }

  @DisplayName("[channelRepository: findAll: success] 채널 전체 조회 성공 테스트")
  @Test
  void findAllSuccessTest() {
    var channel = createBeforeSavedChannel();
    channelRepository.save(channel);

    var foundChannels = channelRepository.findAll();

    Assertions.assertThat(foundChannels.size()).isEqualTo(1);
  }

  @DisplayName("[channelRepository: findById: success] id 조건 조회 성공 테스트")
  @Test
  void findByIdSuccessTest() {
    var channel = createBeforeSavedChannel();
    var savedChannel = channelRepository.save(channel);

    var nullableChannel = channelRepository.findById(savedChannel.getId());

    Assertions.assertThat(nullableChannel).isNotEmpty();
    var foundChannel = nullableChannel.get();
    Assertions.assertThat(foundChannel).isEqualTo(savedChannel);
  }
}