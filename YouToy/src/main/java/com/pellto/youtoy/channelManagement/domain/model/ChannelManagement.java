package com.pellto.youtoy.channelManagement.domain.model;

import com.pellto.youtoy.global.dto.channelManagement.ChannelManagementDto;
import com.pellto.youtoy.global.util.General;
import com.pellto.youtoy.global.util.Temporal;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ChannelManagement {

  private final Long id;
  private final Long channelId;
  private final Long memberId;
  private final LocalDateTime createdAt;
  private ChannelManagementLevel manageLevel;

  @Builder
  public ChannelManagement(Long id, Long channelId, Long memberId,
      ChannelManagementLevel manageLevel,
      LocalDateTime createdAt) {
    this.id = id;
    this.channelId = Objects.requireNonNull(channelId);
    this.memberId = Objects.requireNonNull(memberId);
    this.manageLevel = General.setNullInput(manageLevel, ChannelManagementLevel.VIEWER);
    this.createdAt = Temporal.createdAt(createdAt);
  }

  public void changeManageLevel(ChannelManagementLevel level) {
    this.manageLevel = level;
  }

  public ChannelManagementDto toDto() {
    return new ChannelManagementDto(
        this.id,
        this.channelId,
        this.memberId,
        this.manageLevel.getLevel(),
        this.createdAt
    );
  }
}
