package com.pellto.youtoy.application.usecase;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.times;

import com.pellto.youtoy.domain.user.service.UserReadService;
import com.pellto.youtoy.domain.view.service.ViewHistoryReadService;
import com.pellto.youtoy.util.error.ErrorCode;
import com.pellto.youtoy.util.view.ViewHistoryFixtureFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@Tag("usecase")
@ExtendWith(MockitoExtension.class)
@DisplayName("[GetUserViewHistoryUsecase Test]")
public class GetUserViewHistoryUsecaseTest {

  private static final Long USER_ID = 1L;
  @InjectMocks
  private GetUserViewHistoryUsecase getUserViewHistoryUsecase;
  @Mock
  private UserReadService userReadService;
  @Mock
  private ViewHistoryReadService viewHistoryReadService;

  @DisplayName("[execute: not exist user] 존재하지 않는 유저의 시청 기록 가져오기 실패 테스트")
  @Test
  public void executeNotExistUserTest() {
    given(userReadService.isExist(USER_ID)).willReturn(true);

    try {
      getUserViewHistoryUsecase.execute(USER_ID);
    } catch (Exception e) {
      assertEquals(ErrorCode.NOT_EXIST_USER.getMessage(), e.getMessage());
      then(userReadService).should(times(1)).isExist(USER_ID);
      then(viewHistoryReadService).should(times(0)).getByUserId(any());
    }
  }

  @DisplayName("[execute: success] 시청 기록 가져오기 성공 테스트")
  @Test
  public void executeTest() {
    var viewHistories = ViewHistoryFixtureFactory.createList(5);

    given(userReadService.isExist(USER_ID)).willReturn(true);
    given(viewHistoryReadService.getByUserId(USER_ID)).willReturn(viewHistories);

    var gottenViewHistories = getUserViewHistoryUsecase.execute(USER_ID);

    assertEquals(viewHistories.size(), gottenViewHistories.size());
    assertEquals(viewHistories, gottenViewHistories);
    then(userReadService).should(times(1)).isExist(USER_ID);
    then(viewHistoryReadService).should(times(1)).getByUserId(USER_ID);
  }
}
