package com.pellto.youtoy.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.times;

import com.pellto.youtoy.domain.channel.service.ChannelAdminReadService;
import com.pellto.youtoy.domain.channel.service.ChannelReadService;
import com.pellto.youtoy.domain.user.service.UserReadService;
import com.pellto.youtoy.util.error.ErrorCode;
import com.pellto.youtoy.util.view.UpdateShortCommandFixtureFactory;
import com.pellto.youtoy.util.view.UpdateVideoCommandFixtureFactory;
import com.pellto.youtoy.util.view.UploadShortCommandFixtureFactory;
import com.pellto.youtoy.util.view.UploadVideoCommandFixtureFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@Tag("usecase")
@ExtendWith(MockitoExtension.class)
public class AdminAuthorizeUsecaseTest {

  @InjectMocks
  private AdminAuthorizeUsecase adminAuthorizeUsecase;

  @Mock
  private UserReadService userReadService;
  @Mock
  private ChannelReadService channelReadService;
  @Mock
  private ChannelAdminReadService channelAdminReadService;

  @Nested
  @DisplayName("With UpdateShortCommand")
  public class WithUpdateShortCommand {

    @DisplayName("[execute: success] 관리자 성공 테스트")
    @Test
    public void executeWithUpdateShortCommandAndAdminTest() {
      var cmd = UpdateShortCommandFixtureFactory.create();

      given(channelReadService.isExist(any())).willReturn(true);
      given(userReadService.isExist(any())).willReturn(true);
      given(channelReadService.isOwner(any(), any())).willReturn(false);
      given(channelAdminReadService.isAdmin(any(), any())).willReturn(true);

      var isAuth = adminAuthorizeUsecase.execute(cmd);

      assertEquals(true, isAuth);
      then(channelReadService).should(times(1)).isExist(any());
      then(userReadService).should(times(1)).isExist(any());
      then(channelReadService).should(times(1)).isOwner(any(), any());
      then(channelAdminReadService).should(times(1)).isAdmin(any(), any());
    }

    @DisplayName("[execute: success] 소유자 성공 테스트")
    @Test
    public void executeWithUpdateShortCommandAndOwnerTest() {
      var cmd = UpdateShortCommandFixtureFactory.create();

      given(channelReadService.isExist(any())).willReturn(true);
      given(userReadService.isExist(any())).willReturn(true);
      given(channelReadService.isOwner(any(), any())).willReturn(true);

      var isAuth = adminAuthorizeUsecase.execute(cmd);

      assertEquals(true, isAuth);
      then(channelReadService).should(times(1)).isExist(any());
      then(userReadService).should(times(1)).isExist(any());
      then(channelReadService).should(times(1)).isOwner(any(), any());
      then(channelAdminReadService).should(times(0)).isAdmin(any(), any());
    }

    @DisplayName("[execute: not authorized User] 권한 없는 유저 실패 테스트")
    @Test
    public void executeWithUpdateShortCommandNotAuthorizedUserTest() {
      var cmd = UpdateShortCommandFixtureFactory.create();

      given(channelReadService.isExist(any())).willReturn(true);
      given(userReadService.isExist(any())).willReturn(true);
      given(channelReadService.isOwner(any(), any())).willReturn(false);
      given(channelAdminReadService.isAdmin(any(), any())).willReturn(false);

      try {
        adminAuthorizeUsecase.execute(cmd);
      } catch (Exception e) {
        assertEquals(ErrorCode.NOT_AUTHORIZED_USER.getMessage(), e.getMessage());
        then(channelReadService).should(times(1)).isExist(any());
        then(userReadService).should(times(1)).isExist(any());
        then(channelReadService).should(times(1)).isOwner(any(), any());
        then(channelAdminReadService).should(times(1)).isAdmin(any(), any());
      }
    }

    @DisplayName("[execute: not exist channel] 존재하지 않는 채널 실패 테스트")
    @Test
    public void executeWithUpdateShortCommandNotExistChannelTest() {
      var cmd = UpdateShortCommandFixtureFactory.create();

      given(channelReadService.isExist(any())).willReturn(false);

      try {
        adminAuthorizeUsecase.execute(cmd);
      } catch (Exception e) {
        assertEquals(ErrorCode.NOT_EXIST_CHANNEL.getMessage(), e.getMessage());
        then(channelReadService).should(times(1)).isExist(any());
        then(userReadService).should(times(0)).isExist(any());
        then(channelReadService).should(times(0)).isOwner(any(), any());
        then(channelAdminReadService).should(times(0)).isAdmin(any(), any());
      }
    }

    @DisplayName("[execute: not exist User] 존재하지 않는 유저 실패 테스트")
    @Test
    public void executeWithUpdateShortCommandNotExistUserTest() {
      var cmd = UpdateShortCommandFixtureFactory.create();

      given(channelReadService.isExist(any())).willReturn(true);
      given(userReadService.isExist(any())).willReturn(false);

      try {
        adminAuthorizeUsecase.execute(cmd);
      } catch (Exception e) {
        assertEquals(ErrorCode.NOT_EXIST_USER.getMessage(), e.getMessage());
        then(channelReadService).should(times(1)).isExist(any());
        then(userReadService).should(times(1)).isExist(any());
        then(channelReadService).should(times(0)).isOwner(any(), any());
        then(channelAdminReadService).should(times(0)).isAdmin(any(), any());
      }
    }
  }

  @Nested
  @DisplayName("With UpdateVideoCommand")
  public class WithUpdateVideoCommand {

    @DisplayName("[execute: success] 관리자 성공 테스트")
    @Test
    public void executeWithUpdateVideoCommandAndAdminTest() {
      var cmd = UpdateVideoCommandFixtureFactory.create();

      given(channelReadService.isExist(any())).willReturn(true);
      given(userReadService.isExist(any())).willReturn(true);
      given(channelReadService.isOwner(any(), any())).willReturn(false);
      given(channelAdminReadService.isAdmin(any(), any())).willReturn(true);

      var isAuth = adminAuthorizeUsecase.execute(cmd);

      assertEquals(true, isAuth);
      then(channelReadService).should(times(1)).isExist(any());
      then(userReadService).should(times(1)).isExist(any());
      then(channelReadService).should(times(1)).isOwner(any(), any());
      then(channelAdminReadService).should(times(1)).isAdmin(any(), any());
    }

    @DisplayName("[execute: success] 소유자 성공 테스트")
    @Test
    public void executeWithUpdateVideoCommandAndOwnerTest() {
      var cmd = UpdateVideoCommandFixtureFactory.create();

      given(channelReadService.isExist(any())).willReturn(true);
      given(userReadService.isExist(any())).willReturn(true);
      given(channelReadService.isOwner(any(), any())).willReturn(true);

      var isAuth = adminAuthorizeUsecase.execute(cmd);

      assertEquals(true, isAuth);
      then(channelReadService).should(times(1)).isExist(any());
      then(userReadService).should(times(1)).isExist(any());
      then(channelReadService).should(times(1)).isOwner(any(), any());
      then(channelAdminReadService).should(times(0)).isAdmin(any(), any());
    }

    @DisplayName("[execute: not authorized User] 권한 없는 유저 실패 테스트")
    @Test
    public void executeWithUpdateVideoCommandNotAuthorizedUserTest() {
      var cmd = UpdateVideoCommandFixtureFactory.create();

      given(channelReadService.isExist(any())).willReturn(true);
      given(userReadService.isExist(any())).willReturn(true);
      given(channelReadService.isOwner(any(), any())).willReturn(false);
      given(channelAdminReadService.isAdmin(any(), any())).willReturn(false);

      try {
        adminAuthorizeUsecase.execute(cmd);
      } catch (Exception e) {
        assertEquals(ErrorCode.NOT_AUTHORIZED_USER.getMessage(), e.getMessage());
        then(channelReadService).should(times(1)).isExist(any());
        then(userReadService).should(times(1)).isExist(any());
        then(channelReadService).should(times(1)).isOwner(any(), any());
        then(channelAdminReadService).should(times(1)).isAdmin(any(), any());
      }
    }

    @DisplayName("[execute: not exist channel] 존재하지 않는 채널 실패 테스트")
    @Test
    public void executeWithUpdateVideoCommandNotExistChannelTest() {
      var cmd = UpdateVideoCommandFixtureFactory.create();

      given(channelReadService.isExist(any())).willReturn(false);

      try {
        adminAuthorizeUsecase.execute(cmd);
      } catch (Exception e) {
        assertEquals(ErrorCode.NOT_EXIST_CHANNEL.getMessage(), e.getMessage());
        then(channelReadService).should(times(1)).isExist(any());
        then(userReadService).should(times(0)).isExist(any());
        then(channelReadService).should(times(0)).isOwner(any(), any());
        then(channelAdminReadService).should(times(0)).isAdmin(any(), any());
      }
    }

    @DisplayName("[execute: not exist User] 존재하지 않는 유저 실패 테스트")
    @Test
    public void executeWithUpdateVideoCommandNotExistUserTest() {
      var cmd = UpdateVideoCommandFixtureFactory.create();

      given(channelReadService.isExist(any())).willReturn(true);
      given(userReadService.isExist(any())).willReturn(false);

      try {
        adminAuthorizeUsecase.execute(cmd);
      } catch (Exception e) {
        assertEquals(ErrorCode.NOT_EXIST_USER.getMessage(), e.getMessage());
        then(channelReadService).should(times(1)).isExist(any());
        then(userReadService).should(times(1)).isExist(any());
        then(channelReadService).should(times(0)).isOwner(any(), any());
        then(channelAdminReadService).should(times(0)).isAdmin(any(), any());
      }
    }
  }

  @Nested
  @DisplayName("With UploadShortCommand")
  public class WithUploadShortCommand {

    @DisplayName("[execute: success] 관리자 성공 테스트")
    @Test
    public void executeWithUploadShortCommandAndAdminTest() {
      var cmd = UploadShortCommandFixtureFactory.create();

      given(channelReadService.isExist(any())).willReturn(true);
      given(userReadService.isExist(any())).willReturn(true);
      given(channelReadService.isOwner(any(), any())).willReturn(false);
      given(channelAdminReadService.isAdmin(any(), any())).willReturn(true);

      var isAuth = adminAuthorizeUsecase.execute(cmd);

      assertEquals(true, isAuth);
      then(channelReadService).should(times(1)).isExist(any());
      then(userReadService).should(times(1)).isExist(any());
      then(channelReadService).should(times(1)).isOwner(any(), any());
      then(channelAdminReadService).should(times(1)).isAdmin(any(), any());
    }

    @DisplayName("[execute: success] 소유자 성공 테스트")
    @Test
    public void executeWithUploadShortCommandAndOwnerTest() {
      var cmd = UploadShortCommandFixtureFactory.create();

      given(channelReadService.isExist(any())).willReturn(true);
      given(userReadService.isExist(any())).willReturn(true);
      given(channelReadService.isOwner(any(), any())).willReturn(true);

      var isAuth = adminAuthorizeUsecase.execute(cmd);

      assertEquals(true, isAuth);
      then(channelReadService).should(times(1)).isExist(any());
      then(userReadService).should(times(1)).isExist(any());
      then(channelReadService).should(times(1)).isOwner(any(), any());
      then(channelAdminReadService).should(times(0)).isAdmin(any(), any());
    }

    @DisplayName("[execute: not authorized User] 권한 없는 유저 실패 테스트")
    @Test
    public void executeWithUploadShortCommandNotAuthorizedUserTest() {
      var cmd = UploadShortCommandFixtureFactory.create();

      given(channelReadService.isExist(any())).willReturn(true);
      given(userReadService.isExist(any())).willReturn(true);
      given(channelReadService.isOwner(any(), any())).willReturn(false);
      given(channelAdminReadService.isAdmin(any(), any())).willReturn(false);

      try {
        adminAuthorizeUsecase.execute(cmd);
      } catch (Exception e) {
        assertEquals(ErrorCode.NOT_AUTHORIZED_USER.getMessage(), e.getMessage());
        then(channelReadService).should(times(1)).isExist(any());
        then(userReadService).should(times(1)).isExist(any());
        then(channelReadService).should(times(1)).isOwner(any(), any());
        then(channelAdminReadService).should(times(1)).isAdmin(any(), any());
      }
    }

    @DisplayName("[execute: not exist channel] 존재하지 않는 채널 실패 테스트")
    @Test
    public void executeWithUploadShortCommandNotExistChannelTest() {
      var cmd = UploadShortCommandFixtureFactory.create();

      given(channelReadService.isExist(any())).willReturn(false);

      try {
        adminAuthorizeUsecase.execute(cmd);
      } catch (Exception e) {
        assertEquals(ErrorCode.NOT_EXIST_CHANNEL.getMessage(), e.getMessage());
        then(channelReadService).should(times(1)).isExist(any());
        then(userReadService).should(times(0)).isExist(any());
        then(channelReadService).should(times(0)).isOwner(any(), any());
        then(channelAdminReadService).should(times(0)).isAdmin(any(), any());
      }
    }

    @DisplayName("[execute: not exist User] 존재하지 않는 유저 실패 테스트")
    @Test
    public void executeWithUploadShortCommandNotExistUserTest() {
      var cmd = UploadShortCommandFixtureFactory.create();

      given(channelReadService.isExist(any())).willReturn(true);
      given(userReadService.isExist(any())).willReturn(false);

      try {
        adminAuthorizeUsecase.execute(cmd);
      } catch (Exception e) {
        assertEquals(ErrorCode.NOT_EXIST_USER.getMessage(), e.getMessage());
        then(channelReadService).should(times(1)).isExist(any());
        then(userReadService).should(times(1)).isExist(any());
        then(channelReadService).should(times(0)).isOwner(any(), any());
        then(channelAdminReadService).should(times(0)).isAdmin(any(), any());
      }
    }
  }

  @Nested
  @DisplayName("With UploadVideoCommand")
  public class WithUploadVideoCommand {

    @DisplayName("[execute: success] 관리자 성공 테스트")
    @Test
    public void executeWithUploadVideoCommandAndAdminTest() {
      var cmd = UploadVideoCommandFixtureFactory.create();

      given(channelReadService.isExist(any())).willReturn(true);
      given(userReadService.isExist(any())).willReturn(true);
      given(channelReadService.isOwner(any(), any())).willReturn(false);
      given(channelAdminReadService.isAdmin(any(), any())).willReturn(true);

      var isAuth = adminAuthorizeUsecase.execute(cmd);

      assertEquals(true, isAuth);
      then(channelReadService).should(times(1)).isExist(any());
      then(userReadService).should(times(1)).isExist(any());
      then(channelReadService).should(times(1)).isOwner(any(), any());
      then(channelAdminReadService).should(times(1)).isAdmin(any(), any());
    }

    @DisplayName("[execute: success] 소유자 성공 테스트")
    @Test
    public void executeWithUploadVideoCommandAndOwnerTest() {
      var cmd = UploadVideoCommandFixtureFactory.create();

      given(channelReadService.isExist(any())).willReturn(true);
      given(userReadService.isExist(any())).willReturn(true);
      given(channelReadService.isOwner(any(), any())).willReturn(true);

      var isAuth = adminAuthorizeUsecase.execute(cmd);

      assertEquals(true, isAuth);
      then(channelReadService).should(times(1)).isExist(any());
      then(userReadService).should(times(1)).isExist(any());
      then(channelReadService).should(times(1)).isOwner(any(), any());
      then(channelAdminReadService).should(times(0)).isAdmin(any(), any());
    }

    @DisplayName("[execute: not authorized User] 권한 없는 유저 실패 테스트")
    @Test
    public void executeWithUploadVideoCommandNotAuthorizedUserTest() {
      var cmd = UploadVideoCommandFixtureFactory.create();

      given(channelReadService.isExist(any())).willReturn(true);
      given(userReadService.isExist(any())).willReturn(true);
      given(channelReadService.isOwner(any(), any())).willReturn(false);
      given(channelAdminReadService.isAdmin(any(), any())).willReturn(false);

      try {
        adminAuthorizeUsecase.execute(cmd);
      } catch (Exception e) {
        assertEquals(ErrorCode.NOT_AUTHORIZED_USER.getMessage(), e.getMessage());
        then(channelReadService).should(times(1)).isExist(any());
        then(userReadService).should(times(1)).isExist(any());
        then(channelReadService).should(times(1)).isOwner(any(), any());
        then(channelAdminReadService).should(times(1)).isAdmin(any(), any());
      }
    }

    @DisplayName("[execute: not exist channel] 존재하지 않는 채널 실패 테스트")
    @Test
    public void executeWithUploadVideoCommandNotExistChannelTest() {
      var cmd = UploadVideoCommandFixtureFactory.create();

      given(channelReadService.isExist(any())).willReturn(false);

      try {
        adminAuthorizeUsecase.execute(cmd);
      } catch (Exception e) {
        assertEquals(ErrorCode.NOT_EXIST_CHANNEL.getMessage(), e.getMessage());
        then(channelReadService).should(times(1)).isExist(any());
        then(userReadService).should(times(0)).isExist(any());
        then(channelReadService).should(times(0)).isOwner(any(), any());
        then(channelAdminReadService).should(times(0)).isAdmin(any(), any());
      }
    }

    @DisplayName("[execute: not exist User] 존재하지 않는 유저 실패 테스트")
    @Test
    public void executeWithUploadVideoCommandNotExistUserTest() {
      var cmd = UploadVideoCommandFixtureFactory.create();

      given(channelReadService.isExist(any())).willReturn(true);
      given(userReadService.isExist(any())).willReturn(false);

      try {
        adminAuthorizeUsecase.execute(cmd);
      } catch (Exception e) {
        assertEquals(ErrorCode.NOT_EXIST_USER.getMessage(), e.getMessage());
        then(channelReadService).should(times(1)).isExist(any());
        then(userReadService).should(times(1)).isExist(any());
        then(channelReadService).should(times(0)).isOwner(any(), any());
        then(channelAdminReadService).should(times(0)).isAdmin(any(), any());
      }
    }
  }
}
