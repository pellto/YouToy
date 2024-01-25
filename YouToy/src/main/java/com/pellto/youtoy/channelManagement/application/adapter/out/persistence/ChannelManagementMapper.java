package com.pellto.youtoy.channelManagement.application.adapter.out.persistence;

import com.pellto.youtoy.channelManagement.domain.model.ChannelManagement;
import com.pellto.youtoy.channelManagement.domain.model.ChannelManagementLevel;
import org.springframework.stereotype.Component;

@Component
public class ChannelManagementMapper {

  public ChannelManagement toDomain(ChannelManagementEntity entity) {
    return ChannelManagement.builder()
        .id(entity.getId())
        .channelId(entity.getChannelId())
        .memberId(entity.getMemberId())
        .manageLevel(ChannelManagementLevel.fromString(entity.getManageLevel()))
        .createdAt(entity.getCreatedAt())
        .build();
  }

  public ChannelManagementEntity toEntity(ChannelManagement management) {
    return ChannelManagementEntity.builder()
        .id(management.getId())
        .channelId(management.getChannelId())
        .memberId(management.getMemberId())
        .manageLevel(management.getManageLevel().getLevel())
        .createdAt(management.getCreatedAt())
        .build();
  }
}
