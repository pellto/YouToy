package com.pellto.youtoy.subscribe.application.adapter.out.persistence;

import com.pellto.youtoy.subscribe.util.SubscribeFixtureFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import({SubscribePersistenceAdapter.class, SubscribeMapper.class})
@Tag("persistenceAdapter")
class SubscribePersistenceAdapterTest {

  private static final String ADAPTER_NAME = "SubscribePersistenceAdapter";

  @Autowired
  private SubscribePersistenceAdapter subscribePersistenceAdapter;

  @DisplayName("[" + ADAPTER_NAME + "/save] 저장 성공 테스트")
  @Test
  void saveSuccessTest() {
    var subscribe = SubscribeFixtureFactory.createBeforeSaved();
    subscribePersistenceAdapter.save(subscribe);

    var savedSubscribe = subscribePersistenceAdapter.save(subscribe);

    Assertions.assertThat(savedSubscribe).isNotNull();
    Assertions.assertThat(savedSubscribe.getId()).isNotNull();
    Assertions.assertThat(savedSubscribe.getCreatedAt()).isNotNull();
    Assertions.assertThat(savedSubscribe.getSubscribedLevel()).isNotNull();
  }

  @DisplayName("[" + ADAPTER_NAME + "/load] 조회 성공 테스트")
  @Test
  void loadSuccessTest() {
    var subscribe = SubscribeFixtureFactory.createBeforeSaved();
    var savedSubscribe = subscribePersistenceAdapter.save(subscribe);

    var loadedSubscribe = subscribePersistenceAdapter.load(savedSubscribe.getId());

    Assertions.assertThat(loadedSubscribe).isNotNull();
    Assertions.assertThat(loadedSubscribe.getId()).isNotNull();
    Assertions.assertThat(loadedSubscribe).usingRecursiveComparison().isEqualTo(savedSubscribe);
  }

  @DisplayName(
      "[" + ADAPTER_NAME
          + "/loadBySubscriberIdAndSubscribedChannelId] 구독자 id, 구독 채널 id 조건 조회 성공 테스트")
  @Test
  void loadBySubscriberIdAndSubscribedChannelIdSuccessTest() {
    var subscribe = SubscribeFixtureFactory.createBeforeSaved();
    var savedSubscribe = subscribePersistenceAdapter.save(subscribe);

    var loadedSubscribe = subscribePersistenceAdapter.loadBySubscriberIdAndSubscribedChannelId(
        subscribe.getSubscriberId(),
        subscribe.getSubscribedChannelId()
    );

    Assertions.assertThat(loadedSubscribe).isNotNull();
    Assertions.assertThat(loadedSubscribe).usingRecursiveComparison().isNotNull();
    Assertions.assertThat(loadedSubscribe).usingRecursiveComparison().isEqualTo(savedSubscribe);
  }

  @DisplayName(
      "[" + ADAPTER_NAME + "/loadsBySubscriberId] 구독자 id 조건 조회 성공 테스트")
  @Test
  void loadsBySubscriberIdSuccessTest() {
    var subscribe = SubscribeFixtureFactory.createBeforeSaved();
    var savedSubscribe = subscribePersistenceAdapter.save(subscribe);

    var loadedSubscribes = subscribePersistenceAdapter.loadsBySubscriberId(
        subscribe.getSubscriberId()
    );

    Assertions.assertThat(loadedSubscribes).isNotNull();
    Assertions.assertThat(loadedSubscribes).isNotEmpty();
    Assertions.assertThat(loadedSubscribes.size()).isEqualTo(1);
  }

  @DisplayName("[" + ADAPTER_NAME + "/delete] 삭제 성공 테스트")
  @Test
  void deleteSuccessTest() {
    var beforeSavedSubscribe = SubscribeFixtureFactory.createBeforeSaved();
    var savedSubscribe = subscribePersistenceAdapter.save(beforeSavedSubscribe);

    subscribePersistenceAdapter.delete(savedSubscribe);

    Assertions.assertThatThrownBy(() -> subscribePersistenceAdapter.load(savedSubscribe.getId()))
        .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("변경 예정");
  }
}