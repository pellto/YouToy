package com.pellto.youtoy.domain.channel.service;

import com.pellto.youtoy.domain.channel.dto.ChannelDto;
import com.pellto.youtoy.domain.channel.entity.Channel;
import com.pellto.youtoy.domain.channel.repository.ChannelRepository;
import com.pellto.youtoy.util.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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

    public boolean isOwner(Long channelId, Long ownerId) {
        try {
            channelRepository.findByChannelIdAndOwnerId(channelId, ownerId).orElseThrow();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean isExist(Long id) {
        var channel = channelRepository.findById(id);
        return channel.isPresent();
    }

    public boolean isExistByHandle(String handle) {
        var channel = channelRepository.findByHandle(handle);
        return channel.isPresent();
    }

    public ChannelDto getByHandle(String handle) {
        var channel = channelRepository.findByHandle(handle).orElse(null);
        Assert.notNull(channel, ErrorCode.NOT_EXIST_CHANNEL_HANDLE.getMessage());
        return toDto(channel);
    }
}
