package com.pellto.youtoy.domain.channel.util;

import com.pellto.youtoy.domain.channel.domain.Channel;
import com.pellto.youtoy.domain.channel.domain.ChannelInfo;
import com.pellto.youtoy.domain.channel.dto.ChannelDto;
import com.pellto.youtoy.domain.channel.dto.CreateChannelRequest;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import com.pellto.youtoy.global.util.RandomString;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ChannelFactory {

  private static final Long ID = 1L;

  private static final ChannelInfo CHANNEL_INFO = new ChannelInfo(
      "@test-handle",
      "displayName",
      "description",
      "banner",
      "profile"
  );
  private static final Long SUBSCRIBER_COUNT = 0L;
  private static final List<Long> SUBSCRIBED_LIST = new ArrayList<>();
  private static final LocalDateTime CREATED_AT = LocalDateTime.now();
  private static final LocalDateTime MODIFIED_AT = CREATED_AT;
  private static final UserUUID OWNER_UUID = new UserUUID(RandomString.make());

  // for subscribe
  private static final Long SUBSCRIBER_ID = 2L;
  private static final Long SUBSCRIBED_ID = 3L;

  public static Channel createBeforeSavedChannel() {
    return Channel.builder()
        .channelInfo(CHANNEL_INFO)
        .ownerUuid(OWNER_UUID)
        .build();
  }

  public static Channel createChannel() {
    return Channel.builder()
        .id(ID)
        .channelInfo(CHANNEL_INFO)
        .ownerUuid(OWNER_UUID)
        .createdAt(CREATED_AT)
        .modifiedAt(MODIFIED_AT)
        .build();
  }

  public static Channel createChannel(Long id) {
    return Channel.builder()
        .id(id)
        .channelInfo(CHANNEL_INFO)
        .ownerUuid(OWNER_UUID)
        .createdAt(CREATED_AT)
        .modifiedAt(MODIFIED_AT)
        .build();
  }

  public static ChannelDto createChannelDto() {
    return new ChannelDto(
        ID,
        OWNER_UUID,
        CHANNEL_INFO,
        SUBSCRIBER_COUNT,
        SUBSCRIBED_LIST,
        CREATED_AT
    );
  }

  public static CreateChannelRequest createChannelRequest() {
    return new CreateChannelRequest(
        OWNER_UUID.getValue(),
        CHANNEL_INFO
    );
  }

  public static Channel createSubscriber() {
    return Channel.builder()
        .id(SUBSCRIBER_ID)
        .channelInfo(CHANNEL_INFO)
        .ownerUuid(OWNER_UUID)
        .createdAt(CREATED_AT)
        .modifiedAt(MODIFIED_AT)
        .build();
  }

  public static Channel createSubscribed() {
    return Channel.builder()
        .id(SUBSCRIBED_ID)
        .channelInfo(CHANNEL_INFO)
        .ownerUuid(OWNER_UUID)
        .createdAt(CREATED_AT)
        .modifiedAt(MODIFIED_AT)
        .build();
  }
}
