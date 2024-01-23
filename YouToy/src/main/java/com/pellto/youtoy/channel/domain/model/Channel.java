package com.pellto.youtoy.channel.domain.model;

import com.pellto.youtoy.global.dto.channel.ChannelDto;
import com.pellto.youtoy.global.dto.channel.ChannelInfoDto;
import com.pellto.youtoy.global.util.General;
import com.pellto.youtoy.global.util.RandomString;
import com.pellto.youtoy.global.util.Temporal;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Channel {

  private final Long id;
  private final Long ownerId;
  private final LocalDateTime createdAt;
  private ChannelInfo channelInfo;
  private ChannelHandle handle;
  private Long subscriberCount;

  @Builder
  public Channel(Long id, Long ownerId, ChannelHandle handle, LocalDateTime createdAt,
      ChannelInfo channelInfo, Long subscriberCount) {
    this.id = id;
    this.ownerId = Objects.requireNonNull(ownerId);
    this.handle = General.setNullInput(handle, this.createInitHandle());
    this.createdAt = Temporal.createdAt(createdAt);
    this.channelInfo = Objects.requireNonNull(channelInfo);
    this.subscriberCount = General.setNullInput(subscriberCount, 0L);
  }

  private ChannelHandle createInitHandle() {
    var handleValue = String.format("@user-%s", RandomString.make());
    return new ChannelHandle(handleValue);
  }

  public ChannelInfoDto getChannelInfoDto() {
    return this.channelInfo.toDto();
  }


  public void changeInfo(ChannelInfo channelInfo) {
    this.channelInfo = channelInfo;
  }

  public void changeHandle(ChannelHandle handle) {
    this.handle = handle;
  }

  public void increaseSubscriber() {
    this.subscriberCount += 1;
  }

  public void decreaseSubscriber() {
    if (this.subscriberCount - 1 < 0) {
      throw new IllegalArgumentException();
    }
    this.subscriberCount -= 1;
  }


  public ChannelDto toDto() {
    return new ChannelDto(
        this.id,
        this.ownerId,
        this.handle.value(),
        this.channelInfo.toDto(),
        this.subscriberCount
    );
  }
}
