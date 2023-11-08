package com.pellto.youtoy.domain.channel.service;

import com.pellto.youtoy.domain.channel.dto.CreateChannelCommand;
import com.pellto.youtoy.domain.channel.entity.Channel;
import com.pellto.youtoy.domain.channel.repository.ChannelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChannelWriteService {
    private final ChannelRepository channelRepository;

    public Channel create(CreateChannelCommand cmd) {
        // TODO: change custom Error with errorHandler
        if (cmd.displayName().isEmpty()) throw new UnsupportedOperationException("채널명은 필수입니다.");
        if (cmd.ownerId() == null) throw new UnsupportedOperationException("유저의 id는 필수입니다.");

        var channel = Channel
                .builder()
                .ownerId(cmd.ownerId())
                .displayName(cmd.displayName())
                .build();

        return channelRepository.save(channel);
    }
}
