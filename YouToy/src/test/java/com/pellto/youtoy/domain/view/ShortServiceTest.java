package com.pellto.youtoy.domain.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.times;

import com.pellto.youtoy.domain.view.repository.ShortRepository;
import com.pellto.youtoy.domain.view.service.ShortWriteService;
import com.pellto.youtoy.util.error.ErrorCode;
import com.pellto.youtoy.util.view.ShortFixtureFactory;
import com.pellto.youtoy.util.view.UpdateShortCommandFixtureFactory;
import com.pellto.youtoy.util.view.UploadShortCommandFixtureFactory;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@Tag("domain")
@ExtendWith(MockitoExtension.class)
public class ShortServiceTest {

  private static final String PREFIX = "Short";
  @InjectMocks
  private ShortWriteService shortWriteService;
  @Mock
  private ShortRepository shortRepository;

  @DisplayName("[" + PREFIX + ": increaseViewCount: not exist short] 쇼츠 view count 증가 시 없는 쇼츠 테스트")
  @Test
  public void increaseViewCountNotExistVideTest() {
    Long id = 1L;

    given(shortRepository.findById(any())).willReturn(null);

    try {
      shortWriteService.increaseViewCount(id);
    } catch (Exception e) {
      assertEquals(NullPointerException.class, e.getClass());
      then(shortRepository).should(times(1)).findById(any());
      then(shortRepository).should(times(0)).save(any());
    }
  }

  @DisplayName("[" + PREFIX + ": increaseViewCount: success] 쇼츠 view count 증가 성공 테스트")
  @Test
  public void increaseViewCountTest() {
    Long id = 1L;
    var shorts = ShortFixtureFactory.create();

    given(shortRepository.findById(any())).willReturn(Optional.ofNullable(shorts));

    shortWriteService.increaseViewCount(id);

    assertNotNull(shorts);
    assertEquals(shorts.getViewCount(), 1L);
    then(shortRepository).should(times(1)).findById(any());
    then(shortRepository).should(times(1)).save(any());
  }

  @DisplayName("[" + PREFIX + ": remove: not exist short] 쇼츠 삭제 시 없는 쇼츠 테스트")
  @Test
  public void removeNotExistShortTest() {
    Long id = 1L;

    given(shortRepository.existShort(any())).willReturn(false);

    try {
      shortWriteService.remove(id);
    } catch (Exception e) {
      assertEquals(ErrorCode.NOT_EXIST_SHORT.getMessage(), e.getMessage());
      then(shortRepository).should(times(1)).existShort(any());
      then(shortRepository).should(times(0)).delete(any());
    }
  }

  @DisplayName("[" + PREFIX + ": remove: success] 쇼츠 삭제 성공 테스트")
  @Test
  public void removeShortTest() {
    Long id = 1L;

    given(shortRepository.existShort(any())).willReturn(true);

    shortWriteService.remove(id);

    then(shortRepository).should(times(1)).existShort(any());
    then(shortRepository).should(times(1)).delete(any());
  }

  @DisplayName("[" + PREFIX + ": update: not exist short] 쇼츠 정보 업데이트시 없는 short id 테스트")
  @Test
  public void updateShortNotExistShortTest() {
    // given
    var cmd = UpdateShortCommandFixtureFactory.create();

    // mocking
    given(shortRepository.findById(any())).willReturn(null);
    try {
      // when
      shortWriteService.update(cmd);
    } catch (Exception e) {
      // then
      assertEquals(e.getClass(), NullPointerException.class);
      then(shortRepository).should(times(1)).findById(any());
      then(shortRepository).should(times(0)).save(any());
    }
  }

  @DisplayName("[" + PREFIX + ": update: success] 쇼츠 정보 업데이트 성공 테스트")
  @Test
  public void updateShortTest() {
    // given
    var shorts = ShortFixtureFactory.create();
    var cmd = UpdateShortCommandFixtureFactory.create();
    var changedShort = ShortFixtureFactory.create(cmd);

    // mocking
    given(shortRepository.findById(any())).willReturn(Optional.ofNullable(shorts));
    given(shortRepository.save(any())).willReturn(changedShort);

    // when
    var updatedShort = shortWriteService.update(cmd);

    // then
    assertNotNull(updatedShort);
    assertNotNull(updatedShort.getId());
    assertEquals(cmd.title(), updatedShort.getTitle());
    assertEquals(cmd.description(), updatedShort.getDescription());
    then(shortRepository).should(times(1)).findById(any());
    then(shortRepository).should(times(1)).save(any());
  }

  @DisplayName("[" + PREFIX + ": upload: success] 쇼츠 업로드 성공 테스트")
  @Test
  // TODO: real upload short test
  public void uploadShortTest() {
    // given
    var cmd = UploadShortCommandFixtureFactory.create();
    var shorts = ShortFixtureFactory.create(cmd);

    // mocking
    given(shortRepository.save(any())).willReturn(shorts);

    // when
    var uploadedShort = shortWriteService.upload(cmd);

    assertEquals(cmd.channelId(), uploadedShort.getChannelId());
    assertEquals(cmd.title(), uploadedShort.getTitle());
    assertEquals(cmd.description(), uploadedShort.getDescription());
    then(shortRepository).should(times(1)).save(any());
  }
}
