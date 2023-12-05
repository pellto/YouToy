package com.pellto.youtoy.application.usecase;

import com.pellto.youtoy.domain.comment.service.CommentReadService;
import com.pellto.youtoy.domain.view.service.ShortReadService;
import com.pellto.youtoy.domain.view.service.VideoReadService;
import com.pellto.youtoy.util.comment.CommentDtoFixtureFactory;
import com.pellto.youtoy.util.error.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.*;

@Tag("usecase")
@ExtendWith(MockitoExtension.class)
@DisplayName("[GetCommentsUsecase Test]")
public class GetCommentsUsecaseTest {
    @InjectMocks
    private GetCommentsUsecase getCommentsUsecase;
    @Mock
    private VideoReadService videoReadService;
    @Mock
    private ShortReadService shortReadService;
    @Mock
    private CommentReadService commentReadService;

    private static final Long VIDEO_ID = 1L;

    @DisplayName("[video: success] 비디오의 댓글 가져오기 성공 테스트")
    @Test
    public void executeVideoTest() {
        var commentDtoList = CommentDtoFixtureFactory.createList(5);
        var isVideo = true;

        given(videoReadService.existVideo(VIDEO_ID)).willReturn(true);
        given(commentReadService.getByVideoIdAndVideo(VIDEO_ID, isVideo)).willReturn(commentDtoList);

        var gottenCommentDtoList = getCommentsUsecase.execute(VIDEO_ID, isVideo);

        assertEquals(commentDtoList.size(), gottenCommentDtoList.size());
        assertEquals(commentDtoList, gottenCommentDtoList);
        then(videoReadService).should(times(1)).existVideo(VIDEO_ID);
        then(shortReadService).should(times(0)).existShort(any());
        then(commentReadService).should(times(1)).getByVideoIdAndVideo(VIDEO_ID, isVideo);
    }

    @DisplayName("[video: not exist video] 존재하지 않는 비디오의 댓글 가져오기 실패 테스트")
    @Test
    public void executeNotExistVideoTest() {
        var isVideo = true;

        given(videoReadService.existVideo(VIDEO_ID)).willReturn(false);

        try {
            getCommentsUsecase.execute(VIDEO_ID, isVideo);
        } catch (Exception e) {
            assertEquals(ErrorCode.NOT_EXIST_VIDEO.getMessage(), e.getMessage());
            then(videoReadService).should(times(1)).existVideo(VIDEO_ID);
            then(shortReadService).should(times(0)).existShort(any());
            then(commentReadService).should(times(0)).getByVideoIdAndVideo(VIDEO_ID, isVideo);
        }
    }

    @DisplayName("[short: success] 쇼츠의 댓글 가져오기 성공 테스트")
    @Test
    public void executeShortTest() {
        var commentDtoList = CommentDtoFixtureFactory.createList(5);
        var isVideo = false;

        given(shortReadService.existShort(VIDEO_ID)).willReturn(true);
        given(commentReadService.getByVideoIdAndVideo(VIDEO_ID, isVideo)).willReturn(commentDtoList);

        var gottenCommentDtoList = getCommentsUsecase.execute(VIDEO_ID, isVideo);

        assertEquals(commentDtoList.size(), gottenCommentDtoList.size());
        assertEquals(commentDtoList, gottenCommentDtoList);
        then(videoReadService).should(times(0)).existVideo(any());
        then(shortReadService).should(times(1)).existShort(VIDEO_ID);
        then(commentReadService).should(times(1)).getByVideoIdAndVideo(VIDEO_ID, isVideo);
    }

    @DisplayName("[short: not exist short] 존재하지 않는 쇼츠의 댓글 가져오기 실패 테스트")
    @Test
    public void executeNotExistShortTest() {
        var isVideo = false;

        given(shortReadService.existShort(VIDEO_ID)).willReturn(false);

        try {
            getCommentsUsecase.execute(VIDEO_ID, isVideo);
        } catch (Exception e) {
            assertEquals(ErrorCode.NOT_EXIST_SHORT.getMessage(), e.getMessage());
            then(videoReadService).should(times(0)).existVideo(any());
            then(shortReadService).should(times(1)).existShort(VIDEO_ID);
            then(commentReadService).should(times(0)).getByVideoIdAndVideo(VIDEO_ID, isVideo);
        }
    }
}
