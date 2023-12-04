package com.pellto.youtoy.domain.view;

import com.pellto.youtoy.domain.view.repository.ViewHistoryRepository;
import com.pellto.youtoy.domain.view.service.ViewHistoryWriteService;
import com.pellto.youtoy.util.view.CreateViewHistoryCommandFixtureFactory;
import com.pellto.youtoy.util.view.ViewHistoryFixtureFactory;
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

@Tag("domain")
@ExtendWith(MockitoExtension.class)
public class ViewHistoryTest {
    private static final String DOMAIN = "View-History";
    @InjectMocks
    private ViewHistoryWriteService viewHistoryWriteService;

    @Mock
    private ViewHistoryRepository viewHistoryRepository;

    @DisplayName("[" + DOMAIN + ": create: success] viewHistory 생성 성공 테스트")
    @Test
    public void createTest() {
        var cmd = CreateViewHistoryCommandFixtureFactory.create();
        var viewHistory = ViewHistoryFixtureFactory.create();

        given(viewHistoryRepository.save(any())).willReturn(viewHistory);

        var createdViewHistory = viewHistoryWriteService.create(cmd);

        assertEquals(cmd.userId(), createdViewHistory.getUserId());
        assertEquals(cmd.videoId(), createdViewHistory.getVideoId());
        assertEquals(cmd.videoType(), createdViewHistory.getVideoType());
        assertEquals(cmd.lastViewAt(), createdViewHistory.getLastViewAt());
        then(viewHistoryRepository).should(times(1)).save(any());
    }

    @DisplayName("[" + DOMAIN + ": delete: success] viewHistory 삭제 성공 테스트")
    @Test
    public void deleteTest() {
        var cmd = CreateViewHistoryCommandFixtureFactory.create();

        viewHistoryWriteService.deleteByCreateCommand(cmd);

        then(viewHistoryRepository).should(times(1)).deleteByUserIdVideoIdVideoType(
                any(),
                any(),
                any()
        );
    }
}
