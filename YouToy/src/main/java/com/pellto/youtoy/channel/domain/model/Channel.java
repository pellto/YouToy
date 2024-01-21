package com.pellto.youtoy.channel.domain.model;

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

  @Builder
  public Channel(Long id, Long ownerId, ChannelHandle handle, LocalDateTime createdAt,
      ChannelInfo channelInfo) {
    this.id = id;
    this.ownerId = Objects.requireNonNull(ownerId);
    this.handle = General.setNullInput(handle, this.createInitHandle());
    this.createdAt = Temporal.createdAt(createdAt);
    this.channelInfo = Objects.requireNonNull(channelInfo);
  }

  private ChannelHandle createInitHandle() {
    var handleValue = String.format("@user-%s", RandomString.make());
    return new ChannelHandle(handleValue);
  }


  public void changeInfo(ChannelInfo channelInfo) {
    this.channelInfo = channelInfo;
  }

  public void changeHandle(ChannelHandle handle) {
    this.handle = handle;
  }
}
