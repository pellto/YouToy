package com.pellto.youtoy.application.usecase;

import com.pellto.youtoy.domain.channel.service.ChannelReadService;
import com.pellto.youtoy.domain.playlist.dto.CreatePlaylistCommand;
import com.pellto.youtoy.domain.playlist.dto.PlaylistDto;
import com.pellto.youtoy.domain.playlist.service.PlaylistReadService;
import com.pellto.youtoy.domain.playlist.service.PlaylistWriteService;
import com.pellto.youtoy.util.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateChannelPlaylistUsecase {
    private final PlaylistWriteService playlistWriteService;
    private final PlaylistReadService playlistReadService;
    private final ChannelReadService channelReadService;

    public PlaylistDto execute(CreatePlaylistCommand cmd) {
        if (!channelReadService.isExist(cmd.channelId())) {
            throw new UnsupportedOperationException(ErrorCode.NOT_EXIST_CHANNEL.getMessage());
        }
        return playlistReadService.toDto(playlistWriteService.create(cmd));
    }
}
