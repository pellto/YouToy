package com.pellto.youtoy.domain.channel.repository;

import com.pellto.youtoy.domain.channel.util.SubscribeFactory;
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
class SubscribeRepositoryTest {

  @Autowired
  private SubscribeRepository subscribeRepository;

  @Autowired
  private ChannelRepository channelRepository;

  @DisplayName("[subscribeRepository: save: success] 구독 저장 성공 테스트")
  @Test
  void saveSuccessTest() {
    var subscribe = SubscribeFactory.createBeforeSavedSubscribe();

    var savedSubscribe = subscribeRepository.save(subscribe);

    Assertions.assertThat(savedSubscribe).isNotNull();
    Assertions.assertThat(savedSubscribe.getId()).isNotNull();
    Assertions.assertThat(savedSubscribe.getSubscriber()).isEqualTo(subscribe.getSubscriber());
    Assertions.assertThat(savedSubscribe.getSubscribed()).isEqualTo(subscribe.getSubscribed());
  }

  @DisplayName("[subscribeRepository: findAll: success] 전체 조회 성공 테스트")
  @Test
  void findAllSuccessTest() {
    var subscribe = SubscribeFactory.createBeforeSavedSubscribe();
    channelRepository.save(subscribe.getSubscriber());
    channelRepository.save(subscribe.getSubscribed());
    subscribeRepository.save(subscribe);

    var foundSubscribes = subscribeRepository.findAll();

    Assertions.assertThat(foundSubscribes.size()).isEqualTo(1);
  }

  @DisplayName("[subscribeRepository: deleteById: success] 전체 조회 성공 테스트")
  @Test
  void deleteByIdSuccessTest() {
    var subscribe = SubscribeFactory.createBeforeSavedSubscribe();
    channelRepository.save(subscribe.getSubscriber());
    channelRepository.save(subscribe.getSubscribed());
    subscribeRepository.save(subscribe);
    Long id = subscribe.getId();

    subscribeRepository.deleteById(id);
    var deleteSubscribe = subscribeRepository.findById(id);
    Assertions.assertThat(deleteSubscribe).isEmpty();
  }
}