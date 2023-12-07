package com.pellto.youtoy.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.times;

import com.pellto.youtoy.domain.user.service.UserReadService;
import com.pellto.youtoy.domain.view.service.ShortWriteService;
import com.pellto.youtoy.domain.view.service.VideoWriteService;
import com.pellto.youtoy.domain.view.service.ViewHistoryReadService;
import com.pellto.youtoy.domain.view.service.ViewHistoryWriteService;
import com.pellto.youtoy.util.error.ErrorCode;
import com.pellto.youtoy.util.types.VideoTypes;
import com.pellto.youtoy.util.view.CreateViewHistoryCommandFixtureFactory;
import com.pellto.youtoy.util.view.ViewHistoryFixtureFactory;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@Tag("usecase")
@ExtendWith(MockitoExtension.class)
@DisplayName("[ViewVideoShortUsecase Test]")
public class ViewVideoShortUsecaseTest {

  private static final LocalDateTime LAST_CREATED_AT = LocalDateTime.MIN;
  @InjectMocks
  private ViewVideoShortUsecase viewVideoShortUsecase;
  @Mock
  private UserReadService userReadService;
  @Mock
  private VideoWriteService videoWriteService;
  @Mock
  private ShortWriteService shortWriteService;
  @Mock
  private ViewHistoryWriteService viewHistoryWriteService;
  @Mock
  private ViewHistoryReadService viewHistoryReadService;

  @DisplayName("With Error")
  @Nested
  public class WithErrorTest {

    @DisplayName("[execute: not exist user] 존재하지 않은 유저의 비디오 시청 실패 테스트")
    @Test
    public void executeNotExistUserTest() {
      var cmd = CreateViewHistoryCommandFixtureFactory.create();

      given(userReadService.isExist(cmd.userId())).willReturn(false);

      try {
        viewVideoShortUsecase.execute(cmd);
      } catch (Exception e) {
        assertEquals(ErrorCode.NOT_EXIST_USER.getMessage(), e.getMessage());
        then(userReadService).should(times(1)).isExist(cmd.userId());
        then(viewHistoryReadService).should(times(0)).existByCreateCommand(any());
        then(viewHistoryReadService).should(times(0)).getByCreateCommand(any());
        then(viewHistoryWriteService).should(times(0)).deleteByCreateCommand(any());
        then(videoWriteService).should(times(0)).increaseViewCount(any());
        then(shortWriteService).should(times(0)).increaseViewCount(any());
        then(viewHistoryWriteService).should(times(0)).create(any());
      }
    }

    @DisplayName("[execute: unsupported video type] 존재하지 않은 유형의 비디오 시청 실패 테스트")
    @Test
    public void executeUnsupportedVideoTypeTest() {
      var cmd = CreateViewHistoryCommandFixtureFactory.create(-1);

      given(userReadService.isExist(cmd.userId())).willReturn(true);
      given(viewHistoryReadService.existByCreateCommand(cmd)).willReturn(false);

      try {
        viewVideoShortUsecase.execute(cmd);
      } catch (Exception e) {
        assertEquals(ErrorCode.UNSUPPORTED_VIDEO_TYPE.getMessage(), e.getMessage());
        then(userReadService).should(times(1)).isExist(cmd.userId());
        then(viewHistoryReadService).should(times(1)).existByCreateCommand(any());
        then(viewHistoryReadService).should(times(0)).getByCreateCommand(any());
        then(viewHistoryWriteService).should(times(0)).deleteByCreateCommand(any());
        then(videoWriteService).should(times(0)).increaseViewCount(any());
        then(shortWriteService).should(times(0)).increaseViewCount(any());
        then(viewHistoryWriteService).should(times(0)).create(any());
      }
    }
  }

  @DisplayName("With short")
  @Nested
  public class WithShortTest {

    private static final Integer SHORTS_VIDEO_TYPE = VideoTypes.SHORTS_TYPE.getValue();

    @DisplayName("[execute: success] 쇼츠 시청 성공 및 이전 기록 삭제 테스트")
    @Test
    public void executeAlreadyHistoryTest() {
      var cmd = CreateViewHistoryCommandFixtureFactory.create(SHORTS_VIDEO_TYPE);
      var viewHistoryDto = ViewHistoryFixtureFactory.toDto(
          ViewHistoryFixtureFactory.create(LAST_CREATED_AT)
      );

      given(userReadService.isExist(cmd.userId())).willReturn(true);
      given(viewHistoryReadService.existByCreateCommand(cmd)).willReturn(true);
      given(viewHistoryReadService.getByCreateCommand(cmd)).willReturn(viewHistoryDto);

      viewVideoShortUsecase.execute(cmd);

      then(userReadService).should(times(1)).isExist(cmd.userId());
      then(viewHistoryReadService).should(times(1)).existByCreateCommand(cmd);
      then(viewHistoryReadService).should(times(1)).getByCreateCommand(cmd);
      then(viewHistoryWriteService).should(times(1)).deleteByCreateCommand(cmd);
      then(videoWriteService).should(times(0)).increaseViewCount(any());
      then(shortWriteService).should(times(1)).increaseViewCount(cmd.videoId());
      then(viewHistoryWriteService).should(times(1)).create(cmd);
    }

    @DisplayName("[execute: success] 최근 시청한 쇼츠 시청 성공 및 이전 기록 삭제 테스트")
    @Test
    public void executeRecentViewedTest() {
      var cmd = CreateViewHistoryCommandFixtureFactory.create(SHORTS_VIDEO_TYPE);
      var viewHistoryDto = ViewHistoryFixtureFactory.toDto(
          ViewHistoryFixtureFactory.create(LocalDateTime.now())
      );

      given(userReadService.isExist(cmd.userId())).willReturn(true);
      given(viewHistoryReadService.existByCreateCommand(cmd)).willReturn(true);
      given(viewHistoryReadService.getByCreateCommand(cmd)).willReturn(viewHistoryDto);

      viewVideoShortUsecase.execute(cmd);

      then(userReadService).should(times(1)).isExist(cmd.userId());
      then(viewHistoryReadService).should(times(1)).existByCreateCommand(cmd);
      then(viewHistoryReadService).should(times(1)).getByCreateCommand(cmd);
      then(viewHistoryWriteService).should(times(1)).deleteByCreateCommand(cmd);
      then(videoWriteService).should(times(0)).increaseViewCount(any());
      then(shortWriteService).should(times(0)).increaseViewCount(any());
      then(viewHistoryWriteService).should(times(1)).create(cmd);
    }

    @DisplayName("[execute: success] 쇼츠 시청 성공 테스트")
    @Test
    public void executeTest() {
      var cmd = CreateViewHistoryCommandFixtureFactory.create(SHORTS_VIDEO_TYPE);

      given(userReadService.isExist(cmd.userId())).willReturn(true);
      given(viewHistoryReadService.existByCreateCommand(cmd)).willReturn(false);

      viewVideoShortUsecase.execute(cmd);

      then(userReadService).should(times(1)).isExist(cmd.userId());
      then(viewHistoryReadService).should(times(1)).existByCreateCommand(cmd);
      then(viewHistoryReadService).should(times(0)).getByCreateCommand(any());
      then(viewHistoryWriteService).should(times(0)).deleteByCreateCommand(any());
      then(videoWriteService).should(times(0)).increaseViewCount(any());
      then(shortWriteService).should(times(1)).increaseViewCount(cmd.videoId());
      then(viewHistoryWriteService).should(times(1)).create(cmd);
    }
  }

  @DisplayName("With video")
  @Nested
  public class WithVideoTest {

    @DisplayName("[execute: success] 비디오 시청 성공 및 이전 기록 삭제 테스트")
    @Test
    public void executeAlreadyHistoryTest() {
      var cmd = CreateViewHistoryCommandFixtureFactory.create();
      var viewHistoryDto = ViewHistoryFixtureFactory.toDto(
          ViewHistoryFixtureFactory.create(LAST_CREATED_AT)
      );

      given(userReadService.isExist(cmd.userId())).willReturn(true);
      given(viewHistoryReadService.existByCreateCommand(cmd)).willReturn(true);
      given(viewHistoryReadService.getByCreateCommand(cmd)).willReturn(viewHistoryDto);

      viewVideoShortUsecase.execute(cmd);

      then(userReadService).should(times(1)).isExist(cmd.userId());
      then(viewHistoryReadService).should(times(1)).existByCreateCommand(cmd);
      then(viewHistoryReadService).should(times(1)).getByCreateCommand(cmd);
      then(viewHistoryWriteService).should(times(1)).deleteByCreateCommand(cmd);
      then(videoWriteService).should(times(1)).increaseViewCount(cmd.videoId());
      then(shortWriteService).should(times(0)).increaseViewCount(any());
      then(viewHistoryWriteService).should(times(1)).create(cmd);
    }

    @DisplayName("[execute: success] 최근 시청한 비디오 시청 성공 및 이전 기록 삭제 테스트")
    @Test
    public void executeRecentViewedTest() {
      var cmd = CreateViewHistoryCommandFixtureFactory.create();
      var viewHistoryDto = ViewHistoryFixtureFactory.toDto(
          ViewHistoryFixtureFactory.create(LocalDateTime.now())
      );

      given(userReadService.isExist(cmd.userId())).willReturn(true);
      given(viewHistoryReadService.existByCreateCommand(cmd)).willReturn(true);
      given(viewHistoryReadService.getByCreateCommand(cmd)).willReturn(viewHistoryDto);

      viewVideoShortUsecase.execute(cmd);

      then(userReadService).should(times(1)).isExist(cmd.userId());
      then(viewHistoryReadService).should(times(1)).existByCreateCommand(cmd);
      then(viewHistoryReadService).should(times(1)).getByCreateCommand(cmd);
      then(viewHistoryWriteService).should(times(1)).deleteByCreateCommand(cmd);
      then(videoWriteService).should(times(0)).increaseViewCount(any());
      then(shortWriteService).should(times(0)).increaseViewCount(any());
      then(viewHistoryWriteService).should(times(1)).create(cmd);
    }

    @DisplayName("[execute: success] 비디오 시청 성공 테스트")
    @Test
    public void executeTest() {
      var cmd = CreateViewHistoryCommandFixtureFactory.create();

      given(userReadService.isExist(cmd.userId())).willReturn(true);
      given(viewHistoryReadService.existByCreateCommand(cmd)).willReturn(false);

      viewVideoShortUsecase.execute(cmd);

      then(userReadService).should(times(1)).isExist(cmd.userId());
      then(viewHistoryReadService).should(times(1)).existByCreateCommand(cmd);
      then(viewHistoryReadService).should(times(0)).getByCreateCommand(any());
      then(viewHistoryWriteService).should(times(0)).deleteByCreateCommand(any());
      then(videoWriteService).should(times(1)).increaseViewCount(cmd.videoId());
      then(shortWriteService).should(times(0)).increaseViewCount(any());
      then(viewHistoryWriteService).should(times(1)).create(cmd);
    }
  }
}
