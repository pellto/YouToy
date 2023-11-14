package com.pellto.youtoy.domain.channel;

import static org.mockito.BDDMockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.pellto.youtoy.domain.channel.repository.ChannelRepository;
import com.pellto.youtoy.domain.channel.service.ChannelWriteService;
import com.pellto.youtoy.util.channel.ChannelFixtureFactory;
import com.pellto.youtoy.util.channel.CreateChannelCommandFixtureFactory;
import com.pellto.youtoy.util.error.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ChannelServiceTest {
    @InjectMocks
    private ChannelWriteService channelWriteService;

    @Mock
    private ChannelRepository channelRepository;

    @DisplayName("[Channel: create] 채널 생성 테스트")
    @Test
    public void createChannelTest() {
        // given
        var cmd = CreateChannelCommandFixtureFactory.create();
        var channel = ChannelFixtureFactory.create(cmd);

        // mocking
        given(channelRepository.save(any())).willReturn(channel);

        // when
        var retChannel = channelWriteService.create(cmd);

        assertEquals(cmd.ownerId(), retChannel.getOwnerId());
        assertEquals(cmd.displayName(), retChannel.getDisplayName());
        then(channelRepository).should(times(1)).save(any());
    }

    @DisplayName("[Channel: create: displayName is required] 채널 생성시 display name 필수 테스트")
    @Test
    public void createChannelDisplayNameIsRequired() {
        // given
        String displayName = "";
        var cmd = CreateChannelCommandFixtureFactory.createWithDisplayName(displayName);

        try {
            // when
            channelWriteService.create(cmd);
        } catch (Exception e) {
            // then
            assertEquals(e.getMessage(), ErrorCode.CHANNEL_DISPLAY_NAME_IS_REQUIRED.getMessage());
        }
        then(channelRepository).should(times(0)).save(any());
    }

    @DisplayName("[Channel: create: ownerId is required] 채널 생성시 owner id 필수 테스트")
    @Test
    public void createChannelOwnerIdIsRequired() {
        // given
        Long ownerId = null;
        var cmd = CreateChannelCommandFixtureFactory.createWithOwnerId(ownerId);

        try {
            // when
            channelWriteService.create(cmd);
        } catch (Exception e) {
            // then
            assertEquals(e.getMessage(), ErrorCode.OWNER_ID_IS_REQUIRED.getMessage());
        }
        then(channelRepository).should(times(0)).save(any());
    }
}
