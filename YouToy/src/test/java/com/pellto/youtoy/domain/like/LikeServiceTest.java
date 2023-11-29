package com.pellto.youtoy.domain.like;

import com.pellto.youtoy.domain.like.repository.LikeRepository;
import com.pellto.youtoy.domain.like.service.LikeReadService;
import com.pellto.youtoy.domain.like.service.LikeWriteService;
import com.pellto.youtoy.util.error.ErrorCode;
import com.pellto.youtoy.util.like.CreateLikeCommandFixtureFactory;
import com.pellto.youtoy.util.like.LikeFixtureFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@Tag("domain")
@ExtendWith(MockitoExtension.class)
public class LikeServiceTest {
    private static final String DOMAIN = "Like";
    @InjectMocks
    private LikeWriteService likeWriteService;
    @InjectMocks
    private LikeReadService likeReadService;

    @Mock
    private LikeRepository likeRepository;

    @DisplayName("[" + DOMAIN + ": like: success] like 생성 성공 테스트")
    @Test
    public void likeTest() {
        var cmd = CreateLikeCommandFixtureFactory.create();
        var like = LikeFixtureFactory.create();

        given(likeRepository.save(any())).willReturn(like);

        var createdLike = likeWriteService.like(cmd);

        assertEquals(cmd.videoId(), createdLike.getVideoId());
        assertEquals(cmd.videoType(), createdLike.getVideoType());
        assertEquals(cmd.commentId(), createdLike.getCommentId());
        assertEquals(cmd.userId(), createdLike.getUserId());
        then(likeRepository).should(times(1)).save(any());
    }

    @DisplayName("[" + DOMAIN + ": cancel: byId success] id로 like 취소 성공 테스트")
    @Test
    public void likeCancelByIdTest() {
        var likeId = 1L;

        given(likeRepository.existById(any())).willReturn(true);

        likeWriteService.cancel(likeId);

        then(likeRepository).should(times(1)).existById(any());
        then(likeRepository).should(times(1)).deleteById(any());
    }

    @DisplayName("[" + DOMAIN + ": cancel: byId Not Exist] id로 존재하지 않는 like 취소 실패 테스트")
    @Test
    public void likeCancelByIdNotExistTest() {
        var likeId = 1L;

        given(likeRepository.existById(any())).willReturn(false);

        try {
            likeWriteService.cancel(likeId);
        } catch (Exception e) {
            assertEquals(e.getMessage(), ErrorCode.NOT_EXIST_LIKE.getMessage());
            then(likeRepository).should(times(1)).existById(any());
            then(likeRepository).should(times(0)).deleteById(any());
        }
    }

    @DisplayName("[" + DOMAIN + ": cancel: byDto success] dto로 like 취소 성공 테스트")
    @Test
    public void likeCancelByDtoTest() {
        var likeDto = likeReadService.toDto(LikeFixtureFactory.create());

        likeWriteService.cancel(likeDto);

        then(likeRepository).should(times(0)).existById(any());
        then(likeRepository).should(times(1)).deleteById(any());
    }
}
