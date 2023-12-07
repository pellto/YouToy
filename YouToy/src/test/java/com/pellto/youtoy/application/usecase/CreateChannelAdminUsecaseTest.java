package com.pellto.youtoy.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.times;

import com.pellto.youtoy.domain.channel.service.ChannelAdminWriteService;
import com.pellto.youtoy.domain.channel.service.ChannelReadService;
import com.pellto.youtoy.domain.user.service.UserReadService;
import com.pellto.youtoy.util.channel.CreateChannelAdminCommandFixtureFactory;
import com.pellto.youtoy.util.error.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@Tag("usecase")
@ExtendWith(MockitoExtension.class)
@DisplayName("[CreateChannelAdminUsecase Test]")
public class CreateChannelAdminUsecaseTest {

  @InjectMocks
  private CreateChannelAdminUsecase createChannelAdminUsecase;
  @Mock
  private ChannelAdminWriteService channelAdminWriteService;
  @Mock
  private ChannelReadService channelReadService;
  @Mock
  private UserReadService userReadService;

  @DisplayName("[execute: not exist applicant] 존재하지 않는 유저의 신청 실패 테스트")
  @Test
  public void executeNotExistApplicantTest() {
    var cmd = CreateChannelAdminCommandFixtureFactory.create();

    given(channelReadService.isExist(any())).willReturn(true);
    given(userReadService.isExist(cmd.userId())).willReturn(false);

    try {
      createChannelAdminUsecase.execute(cmd);
    } catch (Exception e) {
      assertEquals(ErrorCode.NOT_EXIST_USER.getMessage(), e.getMessage());
      then(channelAdminWriteService).should(times(0)).create(any());
      then(channelReadService).should(times(1)).isExist(any());
      then(userReadService).should(times(1)).isExist(any());
    }
  }

  @DisplayName("[execute: not exist channel] 존재하지 않는 채널에 관리자 생성 실패 테스트")
  @Test
  public void executeNotExistChannelTest() {
    var cmd = CreateChannelAdminCommandFixtureFactory.create();

    given(channelReadService.isExist(any())).willReturn(false);

    try {
      createChannelAdminUsecase.execute(cmd);
    } catch (Exception e) {
      assertEquals(ErrorCode.NOT_EXIST_CHANNEL.getMessage(), e.getMessage());
      then(channelAdminWriteService).should(times(0)).create(any());
      then(channelReadService).should(times(1)).isExist(any());
      then(userReadService).should(times(0)).isExist(any());
    }
  }

  @DisplayName("[execute: not exist owner] 존재하지 않는 관리자의 신청 실패 테스트")
  @Test
  public void executeNotExistOwnerTest() {
    var cmd = CreateChannelAdminCommandFixtureFactory.create();

    given(channelReadService.isExist(any())).willReturn(true);
    given(userReadService.isExist(cmd.userId())).willReturn(true);
    given(userReadService.isExist(cmd.ownerId())).willReturn(false);

    try {
      createChannelAdminUsecase.execute(cmd);
    } catch (Exception e) {
      assertEquals(ErrorCode.NOT_EXIST_USER.getMessage(), e.getMessage());
      then(channelAdminWriteService).should(times(0)).create(any());
      then(channelReadService).should(times(1)).isExist(any());
      then(userReadService).should(times(2)).isExist(any());
    }
  }

  @DisplayName("[execute: success] 채널 관리자 생성 성공 테스트")
  @Test
  public void executeTest() {
    var cmd = CreateChannelAdminCommandFixtureFactory.create();

    given(channelReadService.isExist(any())).willReturn(true);
    given(userReadService.isExist(any())).willReturn(true);
    given(channelReadService.isOwner(any(), any())).willReturn(true);

    createChannelAdminUsecase.execute(cmd);

    then(channelAdminWriteService).should(times(1)).create(any());
    then(channelReadService).should(times(1)).isExist(any());
    then(userReadService).should(times(2)).isExist(any());
  }

  @DisplayName("[execute: user is not owner] 관리 권한 없는 사람의 신청 실패 테스트")
  @Test
  public void executeUserIsNotOwnerTest() {
    var cmd = CreateChannelAdminCommandFixtureFactory.create();

    given(channelReadService.isExist(any())).willReturn(true);
    given(userReadService.isExist(cmd.userId())).willReturn(true);
    given(userReadService.isExist(cmd.ownerId())).willReturn(true);
    given(channelReadService.isOwner(any(), any())).willReturn(false);

    try {
      createChannelAdminUsecase.execute(cmd);
    } catch (Exception e) {
      assertEquals(ErrorCode.USER_IS_NOT_OWNER.getMessage(), e.getMessage());
      then(channelAdminWriteService).should(times(0)).create(any());
      then(channelReadService).should(times(1)).isExist(any());
      then(userReadService).should(times(2)).isExist(any());
    }
  }
}
