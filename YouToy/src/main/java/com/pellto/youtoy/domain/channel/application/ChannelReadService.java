package com.pellto.youtoy.domain.channel.application;

import com.pellto.youtoy.domain.channel.domain.Channel;
import com.pellto.youtoy.domain.channel.dto.ChannelDto;
import com.pellto.youtoy.domain.channel.exception.NotExistChannelException;
import com.pellto.youtoy.domain.channel.repository.ChannelRepository;
import com.pellto.youtoy.global.error.ErrorCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChannelReadService {

  private final ChannelRepository channelRepository;

  public List<ChannelDto> findAll() {
    return channelRepository.findAll().stream().map(this::toDto).toList();
  }

  public ChannelDto findById(Long id) {
    var nullableChannel = channelRepository.findById(id).orElse(null);
    if (nullableChannel == null) {
      throw new NotExistChannelException(ErrorCode.NOT_EXIST_CHANNEL);
    }
    System.out.println("nullableChannel = " + nullableChannel);
    return toDto(nullableChannel);
  }

  public boolean existById(Long id) {
    return channelRepository.existsById(id);
  }

  public Channel getById(Long id) {
    return channelRepository.getReferenceById(id);
  }

  public ChannelDto toDto(Channel channel) {
    return new ChannelDto(
        channel.getId(),
        channel.getOwnerUuid(),
        channel.getChannelInfo(),
        channel.getSubscriberCount(),
        channel.getSubscribedList(),
        channel.getCreatedAt()
    );
  }
}
