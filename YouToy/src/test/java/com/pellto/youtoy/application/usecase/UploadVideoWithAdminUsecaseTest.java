package com.pellto.youtoy.application.usecase;

import com.pellto.youtoy.domain.view.service.VideoWriteService;
import com.pellto.youtoy.util.view.UploadVideoCommandFixtureFactory;
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
@DisplayName("[UploadVideoWithAdminUsecase Test]")
public class UploadVideoWithAdminUsecaseTest {
    @InjectMocks
    private UploadVideoWithAdminUsecase uploadVideoWithAdminUsecase;
    @Mock
    private VideoWriteService videoWriteService;
    @Mock
    private AdminAuthorizeUsecase adminAuthorizeUsecase;

    @DisplayName("[execute: success] 관리자의 비디오 업로드 성공 테스트")
    @Test
    public void executeTest() {
        var cmd = UploadVideoCommandFixtureFactory.create();
        var video = VideoFixtureFactory.create(cmd);

        given(adminAuthorizeUsecase.execute(cmd)).willReturn(true);
        given(videoWriteService.upload(cmd)).willReturn(video);

        var uploadedVideo = uploadVideoWithAdminUsecase.execute(cmd);

        assertEquals(video, uploadedVideo);
        then(adminAuthorizeUsecase).should(times(1)).execute(cmd);
        then(videoWriteService).should(times(1)).upload(cmd);
    }

    @DisplayName("[execute: not auth] 관리자가 아닌 유저의 비디오 업로드 실패 테스트")
    @Test
    public void executeNotAuthTest() {
        var cmd = UploadVideoCommandFixtureFactory.create();

        given(adminAuthorizeUsecase.execute(cmd)).willReturn(false);

        var uploadedVideo = uploadVideoWithAdminUsecase.execute(cmd);

        assertNull(uploadedVideo);
        then(adminAuthorizeUsecase).should(times(1)).execute(cmd);
        then(videoWriteService).should(times(0)).upload(cmd);
    }
}
