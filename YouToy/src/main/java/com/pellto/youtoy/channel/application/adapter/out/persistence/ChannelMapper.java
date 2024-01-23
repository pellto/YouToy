package com.pellto.youtoy.channel.application.adapter.out.persistence;

import com.pellto.youtoy.channel.domain.model.Channel;
import com.pellto.youtoy.channel.domain.model.ChannelHandle;
import com.pellto.youtoy.channel.domain.model.ChannelInfo;
import org.springframework.stereotype.Component;

@Component
class ChannelMapper {

  public Channel toDomain(ChannelEntity entity) {
    var info = new ChannelInfo(
        entity.getDisplayName(), entity.getDescription(),
        entity.getBannerPath(), entity.getProfilePath()
    );
    var handle = new ChannelHandle(entity.getHandle());

    return Channel.builder()
        .id(entity.getId())
        .ownerId(entity.getOwnerId())
        .channelInfo(info)
        .handle(handle)
        .createdAt(entity.getCreatedAt())
        .subscriberCount(entity.getSubscriberCount())
        .build();
  }

  public ChannelEntity toEntity(Channel channel) {
    return ChannelEntity.builder()
        .id(channel.getId())
        .ownerId(channel.getOwnerId())
        .displayName(channel.getChannelInfo().getDisplayName())
        .description(channel.getChannelInfo().getDescription())
        .bannerPath(channel.getChannelInfo().getBannerPath())
        .profilePath(channel.getChannelInfo().getProfilePath())
        .handle(channel.getHandle().value())
        .subscriberCount(channel.getSubscriberCount())
        .createdAt(channel.getCreatedAt())
        .build();
  }
}
