package com.pellto.youtoy.domain.channel.service;

import com.pellto.youtoy.domain.channel.dto.ChannelDto;
import com.pellto.youtoy.domain.channel.entity.Channel;
import com.pellto.youtoy.domain.channel.repository.ChannelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ChannelReadService {
    private final ChannelRepository channelRepository;

    public ChannelDto getChannel(Long id) {
        var channel = channelRepository.findById(id).orElseThrow();
        return toDto(channel);
    }

    public ChannelDto toDto(Channel channel) {
        return new ChannelDto(
                channel.getId(),
                channel.getOwnerId(),
                channel.getHandle(),
                channel.getDisplayName()
        );
    }
}
