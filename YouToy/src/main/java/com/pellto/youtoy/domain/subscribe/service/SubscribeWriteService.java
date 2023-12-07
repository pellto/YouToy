package com.pellto.youtoy.domain.subscribe.service;

import com.pellto.youtoy.domain.subscribe.entity.Subscribe;
import com.pellto.youtoy.domain.subscribe.repository.SubscribeRepository;
import com.pellto.youtoy.util.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SubscribeWriteService {

  private final SubscribeRepository subscribeRepository;

  public Subscribe create(Long userId, Long channelId) {
    if (channelId == null) {
      throw new UnsupportedOperationException(ErrorCode.NOT_ENTERED_CHANNEL_ID.getMessage());
    }
    if (userId == null) {
      throw new UnsupportedOperationException(ErrorCode.NOT_ENTERED_USER_ID.getMessage());
    }
    if (subscribeRepository.existSubscribe(channelId, userId)) {
      throw new UnsupportedOperationException(ErrorCode.ALREADY_SUBSCRIBED.getMessage());
    }

    var subscribe = Subscribe
        .builder()
        .channelId(channelId)
        .userId(userId)
        .build();

    return subscribeRepository.save(subscribe);
  }

  public void delete(Long userId, Long channelId) {
    if (channelId == null) {
      throw new UnsupportedOperationException(ErrorCode.NOT_ENTERED_CHANNEL_ID.getMessage());
    }
    if (userId == null) {
      throw new UnsupportedOperationException(ErrorCode.NOT_ENTERED_USER_ID.getMessage());
    }
    if (!subscribeRepository.existSubscribe(channelId, userId)) {
      throw new UnsupportedOperationException(ErrorCode.NOT_EXIST_SUBSCRIBE.getMessage());
    }
    subscribeRepository.delete(channelId, userId);
  }
}
