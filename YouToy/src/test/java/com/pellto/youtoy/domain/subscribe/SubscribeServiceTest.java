package com.pellto.youtoy.domain.subscribe;

import com.pellto.youtoy.domain.subscribe.repository.SubscribeRepository;
import com.pellto.youtoy.domain.subscribe.service.SubscribeWriteService;
import com.pellto.youtoy.util.error.ErrorCode;
import com.pellto.youtoy.util.subscribe.SubscribeFixtureFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class SubscribeServiceTest {
    @InjectMocks
    private SubscribeWriteService subscribeWriteService;

    @Mock
    private SubscribeRepository subscribeRepository;

    @DisplayName("[Subscribe: create: success] 구독 생성 테스트")
    @Test
    public void createSubscribeTest() {
        // given
        Long userId = 1L;
        Long channelId = 1L;
        var subscribe = SubscribeFixtureFactory.create(userId, channelId);

        // mocking
        given(subscribeRepository.existSubscribe(any(), any())).willReturn(false);
        given(subscribeRepository.save(any())).willReturn(subscribe);

        // when
        var retSubscribe = subscribeWriteService.create(userId, channelId);

        // then
        assertEquals(userId, retSubscribe.getUserId());
        assertEquals(channelId, retSubscribe.getChannelId());
        then(subscribeRepository).should(times(1)).existSubscribe(any(), any());
        then(subscribeRepository).should(times(1)).save(any());
    }

    @DisplayName("[Subscribe: create: not entered channelId] 구독 생성시 channelId 미입력 테스트")
    @Test
    public void createSubscribeNotEnteredChannelIdTest() {
        // given
        Long userId = 1L;
        Long channelId = null;

        try {
            // when
            subscribeWriteService.create(userId, channelId);
        } catch (Exception e) {
            // then
            assertEquals(e.getMessage(), ErrorCode.NOT_ENTERED_CHANNEL_ID.getMessage());
            then(subscribeRepository).should(times(0)).existSubscribe(any(), any());
            then(subscribeRepository).should(times(0)).save(any());
        }
    }

    @DisplayName("[Subscribe: create: not entered userId] 구독 생성시 userId 미입력 테스트")
    @Test
    public void createSubscribeNotEnteredUserIdTest() {
        // given
        Long userId = null;
        Long channelId = 1L;

        try {
            // when
            subscribeWriteService.create(userId, channelId);
        } catch (Exception e) {
            // then
            assertEquals(e.getMessage(), ErrorCode.NOT_ENTERED_USER_ID.getMessage());
            then(subscribeRepository).should(times(0)).existSubscribe(any(), any());
            then(subscribeRepository).should(times(0)).save(any());
        }
    }

    @DisplayName("[Subscribe: create: already subscribe] 구독 생성시 이미 구독 테스트")
    @Test
    public void createSubscribeAlreadySubscribeTest() {
        // given
        Long userId = 1L;
        Long channelId = 1L;

        // mocking
        given(subscribeRepository.existSubscribe(any(), any())).willReturn(true);

        try {
            // when
            subscribeWriteService.create(userId, channelId);
        } catch (Exception e) {
            // then
            assertEquals(e.getMessage(), ErrorCode.ALREADY_SUBSCRIBED.getMessage());
            then(subscribeRepository).should(times(1)).existSubscribe(any(), any());
            then(subscribeRepository).should(times(0)).save(any());
        }
    }

    @DisplayName("[Subscribe: delete: success] 구독 취소 테스트")
    @Test
    public void deleteTest() {
        // given
        Long userId = 1L;
        Long channelId = 1L;

        // mocking
        given(subscribeRepository.existSubscribe(any(), any())).willReturn(true);

        // when
        subscribeWriteService.delete(userId, channelId);

        // then
        then(subscribeRepository).should(times(1)).existSubscribe(any(), any());
        then(subscribeRepository).should(times(1)).delete(any(), any());
    }

    @DisplayName("[Subscribe: delete: not entered channelId] 구독 취소시 channelId 미입력 테스트")
    @Test
    public void deleteSubscribeNotEnteredChannelIdTest() {
        // given
        Long userId = 1L;
        Long channelId = null;

        try {
            // when
            subscribeWriteService.delete(userId, channelId);
        } catch (Exception e) {
            // then
            assertEquals(e.getMessage(), ErrorCode.NOT_ENTERED_CHANNEL_ID.getMessage());
            then(subscribeRepository).should(times(0)).existSubscribe(any(), any());
            then(subscribeRepository).should(times(0)).save(any());
        }
    }

    @DisplayName("[Subscribe: delete: not entered userId] 구독 취소시 userId 미입력 테스트")
    @Test
    public void deleteSubscribeNotEnteredUserIdTest() {
        // given
        Long userId = null;
        Long channelId = 1L;

        try {
            // when
            subscribeWriteService.delete(userId, channelId);
        } catch (Exception e) {
            // then
            assertEquals(e.getMessage(), ErrorCode.NOT_ENTERED_USER_ID.getMessage());
            then(subscribeRepository).should(times(0)).existSubscribe(any(), any());
            then(subscribeRepository).should(times(0)).save(any());
        }
    }

    @DisplayName("[Subscribe: delete: not exist subscribe] 구독 취소시 구독 안된 관계 테스트")
    @Test
    public void deleteSubscribeNotExistTest() {
        // given
        Long userId = 1L;
        Long channelId = 1L;

        // mocking
        given(subscribeRepository.existSubscribe(any(), any())).willReturn(false);

        try {
            // when
            subscribeWriteService.delete(userId, channelId);
        } catch (Exception e) {
            // then
            assertEquals(e.getMessage(), ErrorCode.NOT_EXIST_SUBSCRIBE.getMessage());
            then(subscribeRepository).should(times(1)).existSubscribe(any(), any());
            then(subscribeRepository).should(times(0)).save(any());
        }
    }
}
