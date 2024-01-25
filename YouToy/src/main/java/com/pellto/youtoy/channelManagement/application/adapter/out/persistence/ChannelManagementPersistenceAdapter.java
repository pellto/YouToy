package com.pellto.youtoy.channelManagement.application.adapter.out.persistence;

import com.pellto.youtoy.channelManagement.domain.model.ChannelManagement;
import com.pellto.youtoy.channelManagement.domain.port.out.LoadChannelManagementPort;
import com.pellto.youtoy.channelManagement.domain.port.out.SaveChannelManagementPort;
import com.pellto.youtoy.global.interfaces.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class ChannelManagementPersistenceAdapter implements SaveChannelManagementPort,
    LoadChannelManagementPort {

  private final ChannelManagementMapper channelManagementMapper;
  private final ChannelManagementJpaDataRepository jpaDataRepository;

  @Override
  public void delete(ChannelManagement management) {
    return;
  }

  @Override
  public ChannelManagement loadByChannelIdAndMemberId(Long channelId, Long memberId) {
    var entity = jpaDataRepository.findByChannelIdAndMemberId(channelId, memberId)
        .orElseThrow(() -> new IllegalArgumentException("변경 예정"));
    return channelManagementMapper.toDomain(entity);
  }

  @Override
  public ChannelManagement save(ChannelManagement management) {
    var entity = channelManagementMapper.toEntity(management);
    entity = jpaDataRepository.save(entity);
    return channelManagementMapper.toDomain(entity);
  }
}
