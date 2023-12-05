package com.pellto.youtoy.application.usecase;

import com.pellto.youtoy.domain.channel.service.ChannelAdminWriteService;
import com.pellto.youtoy.domain.channel.service.ChannelReadService;
import com.pellto.youtoy.domain.user.service.UserReadService;
import com.pellto.youtoy.util.channel.DeleteChannelAdminCommandFixtureFactory;
import com.pellto.youtoy.util.error.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.*;

@Tag("usecase")
@ExtendWith(MockitoExtension.class)
@DisplayName("[DeleteChannelAdminUsecase Test]")
public class DeleteChannelAdminUsecaseTest {
    @InjectMocks
    private DeleteChannelAdminUsecase deleteChannelAdminUsecase;
    @Mock
    private ChannelAdminWriteService channelAdminWriteService;
    @Mock
    private ChannelReadService channelReadService;
    @Mock
    private UserReadService userReadService;

    @DisplayName("[execute: success] 채널 관리자 권한 삭제 성공 테스트")
    @Test
    public void executeTest() {
        var cmd = DeleteChannelAdminCommandFixtureFactory.create();

        given(channelReadService.isExist(cmd.channelId())).willReturn(true);
        given(userReadService.isExist(cmd.userId())).willReturn(true);
        given(userReadService.isExist(cmd.ownerId())).willReturn(true);
        given(channelReadService.isOwner(cmd.channelId(), cmd.ownerId())).willReturn(true);

        deleteChannelAdminUsecase.execute(cmd);

        then(channelReadService).should(times(1)).isExist(cmd.channelId());
        then(userReadService).should(times(1)).isExist(cmd.userId());
        then(userReadService).should(times(1)).isExist(cmd.ownerId());
        then(channelReadService).should(times(1)).isOwner(cmd.channelId(), cmd.ownerId());
        then(channelAdminWriteService).should(times(1)).delete(cmd);
    }

    @DisplayName("[execute: not exist channel] 없는 채널의 채널 관리자 권한 삭제 실패 테스트")
    @Test
    public void executeNotExistChannelTest() {
        var cmd = DeleteChannelAdminCommandFixtureFactory.create();

        given(channelReadService.isExist(cmd.channelId())).willReturn(false);

        try {
            deleteChannelAdminUsecase.execute(cmd);
        } catch (Exception e) {
            assertEquals(ErrorCode.NOT_EXIST_CHANNEL.getMessage(), e.getMessage());
            then(channelReadService).should(times(1)).isExist(cmd.channelId());
            then(userReadService).should(times(0)).isExist(cmd.userId());
            then(userReadService).should(times(0)).isExist(cmd.ownerId());
            then(channelReadService).should(times(0)).isOwner(cmd.channelId(), cmd.ownerId());
            then(channelAdminWriteService).should(times(0)).delete(cmd);
        }
    }

    @DisplayName("[execute: not exist user by user] 존재하지 않는 유저를 채널 관리자 권한 삭제 실패 테스트")
    @Test
    public void executeNotExistUserByUserTest() {
        var cmd = DeleteChannelAdminCommandFixtureFactory.create();

        given(channelReadService.isExist(cmd.channelId())).willReturn(true);
        given(userReadService.isExist(cmd.userId())).willReturn(false);

        try {
            deleteChannelAdminUsecase.execute(cmd);
        } catch (Exception e) {
            assertEquals(ErrorCode.NOT_EXIST_USER.getMessage(), e.getMessage());
            then(channelReadService).should(times(1)).isExist(cmd.channelId());
            then(userReadService).should(times(1)).isExist(cmd.userId());
            then(userReadService).should(times(0)).isExist(cmd.ownerId());
            then(channelReadService).should(times(0)).isOwner(cmd.channelId(), cmd.ownerId());
            then(channelAdminWriteService).should(times(0)).delete(cmd);
        }
    }

    @DisplayName("[execute: not exist user by owner] 존재하지 않는 소유자가 채널 관리자 권한 삭제 실패 테스트")
    @Test
    public void executeNotExistUserByOwnerTest() {
        var cmd = DeleteChannelAdminCommandFixtureFactory.create();

        given(channelReadService.isExist(cmd.channelId())).willReturn(true);
        given(userReadService.isExist(cmd.userId())).willReturn(true);
        given(userReadService.isExist(cmd.ownerId())).willReturn(false);

        try {
            deleteChannelAdminUsecase.execute(cmd);
        } catch (Exception e) {
            assertEquals(ErrorCode.NOT_EXIST_USER.getMessage(), e.getMessage());
            then(channelReadService).should(times(1)).isExist(cmd.channelId());
            then(userReadService).should(times(1)).isExist(cmd.userId());
            then(userReadService).should(times(1)).isExist(cmd.ownerId());
            then(channelReadService).should(times(0)).isOwner(cmd.channelId(), cmd.ownerId());
            then(channelAdminWriteService).should(times(0)).delete(cmd);
        }
    }

    @DisplayName("[execute: user is not owner] 소유자가 아닌 유저가 채널 관리자 권한 삭제 실패 테스트")
    @Test
    public void executeUserIsNotOwnerTest() {
        var cmd = DeleteChannelAdminCommandFixtureFactory.create();

        given(channelReadService.isExist(cmd.channelId())).willReturn(true);
        given(userReadService.isExist(cmd.userId())).willReturn(true);
        given(userReadService.isExist(cmd.ownerId())).willReturn(true);
        given(channelReadService.isOwner(cmd.channelId(), cmd.ownerId())).willReturn(false);

        try {
            deleteChannelAdminUsecase.execute(cmd);
        } catch (Exception e) {
            assertEquals(ErrorCode.USER_IS_NOT_OWNER.getMessage(), e.getMessage());
            then(channelReadService).should(times(1)).isExist(cmd.channelId());
            then(userReadService).should(times(1)).isExist(cmd.userId());
            then(userReadService).should(times(1)).isExist(cmd.ownerId());
            then(channelReadService).should(times(1)).isOwner(cmd.channelId(), cmd.ownerId());
            then(channelAdminWriteService).should(times(0)).delete(cmd);
        }
    }
}
