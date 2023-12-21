package com.pellto.youtoy.domain.channel.application;

import com.pellto.youtoy.domain.channel.domain.Subscribe;
import com.pellto.youtoy.domain.channel.dto.CreateSubscribeRelRequest;
import com.pellto.youtoy.domain.channel.dto.SubscribeDto;
import com.pellto.youtoy.domain.channel.exception.NotExistSubscribedChannelException;
import com.pellto.youtoy.domain.channel.exception.NotExistSubscriberChannelException;
import com.pellto.youtoy.domain.channel.repository.SubscribeRepository;
import com.pellto.youtoy.global.error.ErrorCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscribeService {

  private final SubscribeRepository subscribeRepository;
  private final ChannelReadService channelReadService;

  public List<SubscribeDto> findAll() {
    return subscribeRepository.findAll().stream().map(this::toDto).toList();
  }

  public SubscribeDto subscribe(CreateSubscribeRelRequest req) {
    if (!channelReadService.existById(req.subscriberId())) {
      throw new NotExistSubscriberChannelException(
          ErrorCode.NOT_EXIST_SUBSCRIBER_CHANNEL);
    }
    if (!channelReadService.existById(req.subscribedId())) {
      throw new NotExistSubscribedChannelException(
          ErrorCode.NOT_EXIST_SUBSCRIBED_CHANNEL);

    }
    var subscriber = channelReadService.getById(req.subscriberId());
    var subscribed = channelReadService.getById(req.subscribedId());
    var subscribe = Subscribe.builder()
        .subscriber(subscriber)
        .subscribed(subscribed)
        .build();
    return toDto(subscribeRepository.save(subscribe));
  }

  public void unsubscribe(Long id) {
    subscribeRepository.deleteById(id);
  }

  public SubscribeDto toDto(Subscribe subscribe) {
    return new SubscribeDto(
        subscribe.getId(),
        subscribe.getSubscriber().getId(),
        subscribe.getSubscribed().getId()
    );
  }
}
