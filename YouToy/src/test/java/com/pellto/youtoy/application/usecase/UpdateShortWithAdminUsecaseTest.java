package com.pellto.youtoy.application.usecase;

import com.pellto.youtoy.domain.view.service.ShortWriteService;
import com.pellto.youtoy.util.view.ShortFixtureFactory;
import com.pellto.youtoy.util.view.UpdateShortCommandFixtureFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@Tag("usecase")
@ExtendWith(MockitoExtension.class)
@DisplayName("[UpdateShortWithAdminUsecase Test]")
public class UpdateShortWithAdminUsecaseTest {
    @InjectMocks
    private UpdateShortWithAdminUsecase updateShortWithAdminUsecase;
    @Mock
    private ShortWriteService shortWriteService;
    @Mock
    private AdminAuthorizeUsecase adminAuthorizeUsecase;

    @DisplayName("[execute: success] 관리자의 쇼츠 업데이트 성공 테스트")
    @Test
    public void executeTest() {
        var cmd = UpdateShortCommandFixtureFactory.create();
        var shorts = ShortFixtureFactory.create(cmd);

        given(adminAuthorizeUsecase.execute(cmd)).willReturn(true);
        given(shortWriteService.update(cmd)).willReturn(shorts);

        var updatedShorts = updateShortWithAdminUsecase.execute(cmd);

        assertEquals(shorts, updatedShorts);
        then(adminAuthorizeUsecase).should(times(1)).execute(cmd);
        then(shortWriteService).should(times(1)).update(cmd);
    }

    @DisplayName("[execute: not auth] 관리자가 아닌 유저의 쇼츠 업데이트 실패 테스트")
    @Test
    public void executeNotAuthTest() {
        var cmd = UpdateShortCommandFixtureFactory.create();

        given(adminAuthorizeUsecase.execute(cmd)).willReturn(false);

        var updatedShorts = updateShortWithAdminUsecase.execute(cmd);

        assertNull(updatedShorts);
        then(adminAuthorizeUsecase).should(times(1)).execute(cmd);
        then(shortWriteService).should(times(0)).update(cmd);
    }
}
