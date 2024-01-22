package com.pellto.youtoy.subscribe.application.adapter.out.persistence;

import com.pellto.youtoy.global.interfaces.PersistenceAdapter;
import com.pellto.youtoy.subscribe.domain.model.Subscribe;
import com.pellto.youtoy.subscribe.domain.port.out.LoadSubscribePort;
import com.pellto.youtoy.subscribe.domain.port.out.SaveSubscribePort;
import lombok.RequiredArgsConstructor;


@PersistenceAdapter
@RequiredArgsConstructor
public class SubscribePersistenceAdapter implements SaveSubscribePort, LoadSubscribePort {

  private final SubscribeMapper subscribeMapper;
  private final SubscribeJpaDataRepository jpaDataRepository;

  @Override
  public Subscribe load(Long id) {
    var subscribe = jpaDataRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("변경 예정"));
    return subscribeMapper.toDomain(subscribe);
  }

  @Override
  public Subscribe loadBySubscriberIdAndSubscribedChannelId(Long subscriberId,
      Long subscribedChannelId) {
    var subscribe = jpaDataRepository.findBySubscriberIdAndSubscribedChannelId(
            subscriberId, subscribedChannelId)
        .orElseThrow(() -> new IllegalArgumentException("변경 예정"));
    return subscribeMapper.toDomain(subscribe);
  }

  @Override
  public Subscribe save(Subscribe subscribe) {
    SubscribeEntity entity = subscribeMapper.toEntity(subscribe);
    entity = jpaDataRepository.save(entity);
    return subscribeMapper.toDomain(entity);
  }

  @Override
  public void delete(Subscribe subscribe) {
    SubscribeEntity entity = subscribeMapper.toEntity(subscribe);
    jpaDataRepository.delete(entity);
  }
}
