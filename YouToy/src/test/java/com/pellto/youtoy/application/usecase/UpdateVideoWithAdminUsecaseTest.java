package com.pellto.youtoy.application.usecase;

import com.pellto.youtoy.domain.view.service.VideoWriteService;
import com.pellto.youtoy.util.view.UpdateVideoCommandFixtureFactory;
import com.pellto.youtoy.util.view.VideoFixtureFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.BDDMockito.*;

@Tag("usecase")
@ExtendWith(MockitoExtension.class)
@DisplayName("[UpdateVideoWithAdminUsecase Test]")
public class UpdateVideoWithAdminUsecaseTest {
    @InjectMocks
    private UpdateVideoWithAdminUsecase updateVideoWithAdminUsecase;
    @Mock
    private VideoWriteService videoWriteService;
    @Mock
    private AdminAuthorizeUsecase adminAuthorizeUsecase;

    @DisplayName("[execute: success] 관리자의 비디오 업데이트 성공 테스트")
    @Test
    public void executeTest() {
        var cmd = UpdateVideoCommandFixtureFactory.create();
        var video = VideoFixtureFactory.create(cmd);

        given(adminAuthorizeUsecase.execute(cmd)).willReturn(true);
        given(videoWriteService.update(cmd)).willReturn(video);

        var updatedVideo = updateVideoWithAdminUsecase.execute(cmd);

        assertEquals(video, updatedVideo);
        then(adminAuthorizeUsecase).should(times(1)).execute(cmd);
        then(videoWriteService).should(times(1)).update(cmd);
    }

    @DisplayName("[execute: not auth] 관리자가 아닌 유저의 비디오 업데이트 실패 테스트")
    @Test
    public void executeNotAuthTest() {
        var cmd = UpdateVideoCommandFixtureFactory.create();

        given(adminAuthorizeUsecase.execute(cmd)).willReturn(false);

        var updatedVideo = updateVideoWithAdminUsecase.execute(cmd);

        assertNull(updatedVideo);
        then(adminAuthorizeUsecase).should(times(1)).execute(cmd);
        then(videoWriteService).should(times(0)).update(cmd);
    }
}
