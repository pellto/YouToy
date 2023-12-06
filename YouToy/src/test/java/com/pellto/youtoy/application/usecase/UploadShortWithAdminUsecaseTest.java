package com.pellto.youtoy.application.usecase;

import com.pellto.youtoy.domain.view.service.ShortWriteService;
import com.pellto.youtoy.util.view.ShortFixtureFactory;
import com.pellto.youtoy.util.view.UploadShortCommandFixtureFactory;
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
@DisplayName("[UploadShortWithAdminUsecase Test]")
public class UploadShortWithAdminUsecaseTest {
    @InjectMocks
    private UploadShortWithAdminUsecase uploadShortWithAdminUsecase;
    @Mock
    private ShortWriteService shortWriteService;
    @Mock
    private AdminAuthorizeUsecase adminAuthorizeUsecase;

    @DisplayName("[execute: success] 관리자의 쇼츠 업로드 성공 테스트")
    @Test
    public void executeTest() {
        var cmd = UploadShortCommandFixtureFactory.create();
        var shorts = ShortFixtureFactory.create(cmd);

        given(adminAuthorizeUsecase.execute(cmd)).willReturn(true);
        given(shortWriteService.upload(cmd)).willReturn(shorts);

        var uploadedShorts = uploadShortWithAdminUsecase.execute(cmd);

        assertEquals(shorts, uploadedShorts);
        then(adminAuthorizeUsecase).should(times(1)).execute(cmd);
        then(shortWriteService).should(times(1)).upload(cmd);
    }

    @DisplayName("[execute: not auth] 관리자가 아닌 유저의 쇼츠 업로드 실패 테스트")
    @Test
    public void executeNotAuthTest() {
        var cmd = UploadShortCommandFixtureFactory.create();

        given(adminAuthorizeUsecase.execute(cmd)).willReturn(false);

        var uploadedShorts = uploadShortWithAdminUsecase.execute(cmd);

        assertNull(uploadedShorts);
        then(adminAuthorizeUsecase).should(times(1)).execute(cmd);
        then(shortWriteService).should(times(0)).upload(cmd);
    }
}
