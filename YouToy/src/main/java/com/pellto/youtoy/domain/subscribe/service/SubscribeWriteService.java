package com.pellto.youtoy.domain.subscribe.service;

import com.pellto.youtoy.domain.subscribe.dto.CreateSubscribeCommand;
import com.pellto.youtoy.domain.subscribe.entity.Subscribe;
import com.pellto.youtoy.domain.subscribe.repository.SubscribeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SubscribeWriteService {
    private final SubscribeRepository subscribeRepository;

    public Subscribe create(Long channelId, Long userId) {
        if (channelId == null) {
            throw new UnsupportedOperationException("구독할 채널의 id는 필수 입니다.");
        }
        if (userId == null) {
            throw new UnsupportedOperationException("구독하는 유저의 id는 필수 입니다.");
        }

        var subscribe = Subscribe
                .builder()
                .channelId(channelId)
                .userId(userId)
                .build();

        return subscribeRepository.save(subscribe);
    }
}
