package com.pellto.youtoy.domain.channel.application;

import com.pellto.youtoy.domain.channel.dao.ChannelRepository;
import com.pellto.youtoy.domain.channel.domain.Channel;
import com.pellto.youtoy.domain.channel.dto.ChannelDto;
import com.pellto.youtoy.domain.channel.exception.ChannelException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
@RequiredArgsConstructor
public class ChannelReadService {

  private final ChannelRepository channelRepository;

  public List<ChannelDto> findAll() {
    return channelRepository.findAll().stream().map(this::toDto).toList();
  }

  public ChannelDto findById(Long id) {
    var nullableChannel = channelRepository.findById(id).orElse(null);
    Assert.notNull(nullableChannel, ChannelException.NOT_EXIST_CHANNEL.getMessage());
    return toDto(nullableChannel);
  }

  public ChannelDto toDto(Channel channel) {
    return new ChannelDto(
        channel.getId(),
        channel.getHandle(),
        channel.getDisplayName(),
        channel.getDescription(),
        channel.getBanner(),
        channel.getProfile(),
        channel.getCreatedAt()
    );
  }
}
