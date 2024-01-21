package com.pellto.youtoy.channel.application.adapter.out.persistence;

import com.pellto.youtoy.channel.domain.model.Channel;
import com.pellto.youtoy.channel.domain.port.out.LoadChannelPort;
import com.pellto.youtoy.channel.domain.port.out.SaveChannelPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class ChannelPersistenceAdapter implements SaveChannelPort, LoadChannelPort {

  private final ChannelMapper channelMapper;
  private final ChannelJpaDataRepository jpaDataRepository;

  @Override
  public Channel load(Long channelId) {
    ChannelEntity entity = jpaDataRepository.findById(channelId).orElseThrow(
        () -> new IllegalArgumentException("변경 예정")
    );

    return channelMapper.toDomain(entity);
  }

  @Override
  public Channel save(Channel channel) {
    ChannelEntity entity = channelMapper.toEntity(channel);
    entity = jpaDataRepository.save(entity);
    return channelMapper.toDomain(entity);
  }
}
