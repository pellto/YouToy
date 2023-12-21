package com.pellto.youtoy.domain.channel.application;

import com.pellto.youtoy.domain.channel.domain.Channel;
import com.pellto.youtoy.domain.channel.dto.ChannelDto;
import com.pellto.youtoy.domain.channel.exception.ChannelException;
import com.pellto.youtoy.domain.channel.repository.ChannelRepository;
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

  public boolean existById(Long id) {
    return channelRepository.existsById(id);
  }

  public Channel getById(Long id) {
    return channelRepository.getReferenceById(id);
  }

  public ChannelDto toDto(Channel channel) {
    Long subscriberCount = (long) channel.getSubscribers().size();
    List<Long> subscribedList = channel.getSubscribeds().stream()
        .map((subscribed) -> subscribed.getSubscribed().getId()).toList();
    return new ChannelDto(
        channel.getId(),
        channel.getHandle(),
        channel.getDisplayName(),
        channel.getDescription(),
        channel.getBanner(),
        channel.getProfile(),
        subscriberCount,
        subscribedList,
        channel.getCreatedAt()
    );
  }
}
