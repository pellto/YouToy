package com.pellto.youtoy.channel.util;

import com.pellto.youtoy.channel.domain.model.Channel;
import com.pellto.youtoy.channel.domain.model.ChannelHandle;
import com.pellto.youtoy.channel.domain.model.ChannelInfo;
import com.pellto.youtoy.comment.domain.model.CommenterInfo;
import java.time.LocalDateTime;

public class ChannelFixtureFactory {

  private static final Long ID = 1L;
  private static final Long OWNER_ID = 1L;
  private static final LocalDateTime CREATED_AT = LocalDateTime.now();
  private static final ChannelInfo CHANNEL_INFO = ChannelInfoFixtureFactory.create();
  private static final ChannelHandle CHANNEL_HANDLE = new ChannelHandle("@handle");

  public static Channel create() {
    return Channel.builder()
        .id(ID)
        .ownerId(OWNER_ID)
        .createdAt(CREATED_AT)
        .channelInfo(CHANNEL_INFO)
        .handle(CHANNEL_HANDLE)
        .build();
  }

  public static CommenterInfo createCommenterInfo() {
    return CommenterInfo.builder()
        .commenterId(ID)
        .commenterHandle(CHANNEL_HANDLE.value())
        .displayName(CHANNEL_INFO.getDisplayName())
        .profilePath(CHANNEL_INFO.getProfilePath())
        .build();
  }

  public static CommenterInfo createCommenterInfo(Long commenterId) {
    return CommenterInfo.builder()
        .commenterId(commenterId)
        .commenterHandle(CHANNEL_HANDLE.value())
        .displayName(CHANNEL_INFO.getDisplayName())
        .profilePath(CHANNEL_INFO.getProfilePath())
        .build();
  }

  public static Channel createWithSubscriberCount(Long subscriberCount) {
    return Channel.builder()
        .id(ID)
        .ownerId(OWNER_ID)
        .createdAt(CREATED_AT)
        .channelInfo(CHANNEL_INFO)
        .handle(CHANNEL_HANDLE)
        .subscriberCount(subscriberCount)
        .build();
  }

  public static Channel createBeforeSaved() {
    return Channel.builder()
        .ownerId(OWNER_ID)
        .channelInfo(CHANNEL_INFO)
        .build();
  }
}
