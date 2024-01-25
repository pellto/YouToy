package com.pellto.youtoy.channelManagement.application.adapter.out.persistence;


import com.pellto.youtoy.channelManagement.domain.model.ChannelManagement;
import com.pellto.youtoy.channelManagement.util.ChannelManagementFixtureFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import({ChannelManagementPersistenceAdapter.class, ChannelManagementMapper.class})
@Tag("persistenceAdapter")
class ChannelManagementPersistenceAdapterTest {

  private static final String ADAPTER_NAME = "ChannelManagementPersistenceAdapter";

  @Autowired
  private ChannelManagementPersistenceAdapter channelManagementPersistenceAdapter;

  @DisplayName("[" + ADAPTER_NAME + "/save] 저장 성공 테스트")
  @Test
  void saveSuccessTest() {
    var before = ChannelManagementFixtureFactory.createBeforeSaved();

    var savedManagement = channelManagementPersistenceAdapter.save(before);

    Assertions.assertThat(savedManagement).isNotNull();
    Assertions.assertThat(savedManagement).usingRecursiveComparison().isNotNull();
    Assertions.assertThat(savedManagement.getClass()).isEqualTo(ChannelManagement.class);
  }

  @DisplayName(
      "[" + ADAPTER_NAME + "/loadByChannelIdAndMemberId] channelId and memberId 조건 조회 성공 테스트")
  @Test
  void loadByChannelIdAndMemberIdSuccessTest() {
    var before = ChannelManagementFixtureFactory.createBeforeSaved();
    var saved = channelManagementPersistenceAdapter.save(before);

    var loadedManagement = channelManagementPersistenceAdapter.loadByChannelIdAndMemberId(
        before.getChannelId(), before.getMemberId());

    Assertions.assertThat(loadedManagement).isNotNull();
    Assertions.assertThat(loadedManagement).usingRecursiveComparison().isNotNull();
    Assertions.assertThat(loadedManagement).usingRecursiveComparison().isEqualTo(saved);
    Assertions.assertThat(loadedManagement.getClass()).isEqualTo(ChannelManagement.class);
  }

  @DisplayName(
      "[" + ADAPTER_NAME + "/loadByChannelIdAndMemberId] channelId and memberId 조건 조회 실패 테스트")
  @Test
  void loadByChannelIdAndMemberIdFailedTest() {
    var before = ChannelManagementFixtureFactory.createBeforeSaved();

    Assertions.assertThatThrownBy(
            () -> channelManagementPersistenceAdapter.loadByChannelIdAndMemberId(
                before.getChannelId(), before.getMemberId()))
        .isInstanceOf(IllegalArgumentException.class).hasMessageContaining("변경 예정");
  }
}