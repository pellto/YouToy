package com.pellto.youtoy.domain.channel;

import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.pellto.youtoy.domain.channel.repository.ChannelAdminRepository;
import com.pellto.youtoy.domain.channel.service.ChannelAdminWriteService;
import com.pellto.youtoy.util.channel.ChannelAdminFixtureFactory;
import com.pellto.youtoy.util.channel.CreateChannelAdminCommandFixtureFactory;
import com.pellto.youtoy.util.channel.DeleteChannelAdminCommandFixtureFactory;
import com.pellto.youtoy.util.error.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@Tag("domain")
@ExtendWith(MockitoExtension.class)
public class ChannelAdminServiceTest {
    private static final String PREFIX = "ChannelAdmin";
    @InjectMocks
    private ChannelAdminWriteService channelAdminWriteService;

    @Mock
    private ChannelAdminRepository channelAdminRepository;

    @DisplayName("[" + PREFIX + ": create: success] 채널 관리자 관계 생성 성공 테스트")
    @Test
    public void createTest() {
        var cmd = CreateChannelAdminCommandFixtureFactory.create();
        var channelAdmin = ChannelAdminFixtureFactory.create(cmd);

        given(channelAdminRepository.findByChannelIdAndUserId(any(), any())).willReturn(Optional.empty());
        given(channelAdminRepository.save(any())).willReturn(channelAdmin);

        var retChannelAdmin = channelAdminWriteService.create(cmd);

        assertEquals(cmd.channelId(), retChannelAdmin.getChannelId());
        assertEquals(cmd.userId(), retChannelAdmin.getUserId());
        then(channelAdminRepository).should(times(1)).findByChannelIdAndUserId(any(), any());
        then(channelAdminRepository).should(times(1)).save(any());
    }

    @DisplayName("[" + PREFIX + ": create: already exist admin] 채널 관리자 관계 생성시 이미 존재하는 관리자 테스트")
    @Test
    public void createAlreadyExistAdminTest() {
        var cmd = CreateChannelAdminCommandFixtureFactory.create();
        var channelAdmin = ChannelAdminFixtureFactory.create(cmd);

        given(channelAdminRepository.findByChannelIdAndUserId(any(), any())).willReturn(Optional.ofNullable(channelAdmin));

        try {
            channelAdminWriteService.create(cmd);
        } catch (Exception e) {
            assertEquals(ErrorCode.ALREADY_EXIST_ADMIN.getMessage(), e.getMessage());
            then(channelAdminRepository).should(times(1)).findByChannelIdAndUserId(any(), any());
            then(channelAdminRepository).should(times(0)).save(any());
        }

    }

    @DisplayName("[" + PREFIX + ": delete: success] 채널 관리자 관계 삭제 성공 테스트")
    @Test
    public void deleteTest() {
        var cmd = DeleteChannelAdminCommandFixtureFactory.create();
        var channelAdmin = ChannelAdminFixtureFactory.create(cmd);

        given(channelAdminRepository.findByChannelIdAndUserId(any(), any())).willReturn(Optional.ofNullable(channelAdmin));

        channelAdminWriteService.delete(cmd);

        then(channelAdminRepository).should(times(1)).findByChannelIdAndUserId(any(), any());
        then(channelAdminRepository).should(times(1)).delete(any(), any());
    }

    @DisplayName("[" + PREFIX + ": delete: not exist admin] 채널 관리자 관계 삭제시 관계자가 아닐 경우 테스트")
    @Test
    public void deleteNotExistAdminTest() {
        var cmd = DeleteChannelAdminCommandFixtureFactory.create();

        given(channelAdminRepository.findByChannelIdAndUserId(any(), any())).willReturn(Optional.empty());

        try {
            channelAdminWriteService.delete(cmd);
        } catch (Exception e) {
            assertEquals(ErrorCode.NOT_EXIST_ADMIN.getMessage(), e.getMessage());
            then(channelAdminRepository).should(times(1)).findByChannelIdAndUserId(any(), any());
            then(channelAdminRepository).should(times(0)).delete(any(), any());
        }
    }

}
