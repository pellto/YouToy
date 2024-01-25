package com.pellto.youtoy.channelManagement.domain.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.channelManagement.domain.model.ChannelManagementLevel;
import com.pellto.youtoy.channelManagement.domain.port.out.ChannelManagementEventPort;
import com.pellto.youtoy.channelManagement.domain.port.out.LoadChannelManagementPort;
import com.pellto.youtoy.channelManagement.domain.port.out.SaveChannelManagementPort;
import com.pellto.youtoy.channelManagement.util.ChannelManagementFixtureFactory;
import com.pellto.youtoy.global.dto.channelManagement.ChannelManagementDto;
import com.pellto.youtoy.global.dto.channelManagement.response.ChangeChannelManagementResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@Tag("service")
class ChannelManagementWriteServiceTest {

  private static final String SERVICE_NAME = "ChannelManagementWriteService";

  @InjectMocks
  private ChannelManagementWriteService channelManagementWriteService;

  @Mock
  private SaveChannelManagementPort saveChannelManagementPort;
  @Mock
  private LoadChannelManagementPort loadChannelManagementPort;
  @Mock
  private ChannelManagementEventPort channelManagementEventPort;

  @DisplayName("[" + SERVICE_NAME + "/changeLevel] changeLevel 성공 테스트")
  @Test
  void changeLevelSuccessTest() {
    var management = ChannelManagementFixtureFactory.create();

    given(loadChannelManagementPort.loadByChannelIdAndMemberId(management.getChannelId(),
        management.getMemberId())).willReturn(management);

    var response = channelManagementWriteService.changeLevel(management.getChannelId(),
        management.getMemberId(), ChannelManagementLevel.CO_WORKER.getLevel());

    Assertions.assertThat(response).isNotNull();
    Assertions.assertThat(response.getClass()).isEqualTo(ChangeChannelManagementResponse.class);
    Assertions.assertThat(response.before().manageLevel())
        .isNotEqualTo(response.after().manageLevel());
    Assertions.assertThat(response.after().manageLevel())
        .isEqualTo(ChannelManagementLevel.CO_WORKER.getLevel());
    then(loadChannelManagementPort).should(times(1)).loadByChannelIdAndMemberId(any(), any());
    then(saveChannelManagementPort).should(times(1)).save(any());
    then(channelManagementEventPort).should(times(1)).channelManagementChangedEvent(any(), any());
  }

  @DisplayName("[" + SERVICE_NAME + "/invite] invite 성공 테스트")
  @Test
  void inviteSuccessTest() {
    var before = ChannelManagementFixtureFactory.createBeforeSaved();
    var saved = ChannelManagementFixtureFactory.create(before);

    given(saveChannelManagementPort.save(any())).willReturn(saved);

    var createdManagement = channelManagementWriteService.createOwner(before.getChannelId(),
        before.getMemberId());

    Assertions.assertThat(createdManagement).isNotNull();
    Assertions.assertThat(createdManagement).usingRecursiveComparison().isNotNull();
    Assertions.assertThat(createdManagement.getClass()).isEqualTo(ChannelManagementDto.class);
    Assertions.assertThat(createdManagement.manageLevel())
        .isEqualTo(ChannelManagementLevel.VIEWER.getLevel());
    Assertions.assertThat(createdManagement).usingRecursiveComparison().isEqualTo(saved.toDto());
    then(saveChannelManagementPort).should(times(1)).save(any());
  }

  @DisplayName("[" + SERVICE_NAME + "/createOwner] createOwner 성공 테스트")
  @Test
  void createOwnerSuccessTest() {
    var before = ChannelManagementFixtureFactory.createBeforeSavedWithOwner();
    var saved = ChannelManagementFixtureFactory.create(before);

    given(saveChannelManagementPort.save(any())).willReturn(saved);

    var createdManagement = channelManagementWriteService.createOwner(before.getChannelId(),
        before.getMemberId());

    Assertions.assertThat(createdManagement).isNotNull();
    Assertions.assertThat(createdManagement).usingRecursiveComparison().isNotNull();
    Assertions.assertThat(createdManagement.getClass()).isEqualTo(ChannelManagementDto.class);
    Assertions.assertThat(createdManagement).usingRecursiveComparison().isEqualTo(saved.toDto());
    Assertions.assertThat(createdManagement.manageLevel())
        .isEqualTo(ChannelManagementLevel.OWNER.getLevel());
    then(saveChannelManagementPort).should(times(1)).save(any());
  }

}