package com.pellto.youtoy.interest.domain.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.global.dto.interest.InterestDto;
import com.pellto.youtoy.interest.domain.port.out.InterestEventPort;
import com.pellto.youtoy.interest.domain.port.out.LoadInterestPort;
import com.pellto.youtoy.interest.domain.port.out.SaveInterestPort;
import com.pellto.youtoy.interest.domain.port.out.http.InterestContentsExistHandlePort;
import com.pellto.youtoy.interest.util.InterestFixtureFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@Tag("service")
class InterestActionWriteServiceTest {

  private static final String SERVICE_NAME = "InterestActionWriteService";
  @InjectMocks
  private InterestActionWriteService interestActionWriteService;

  @Mock
  private SaveInterestPort saveInterestPort;
  @Mock
  private LoadInterestPort loadInterestPort;
  @Mock
  private InterestEventPort interestEventPort;
  @Mock
  private InterestContentsExistHandlePort interestContentsExistHandlePort;

  @DisplayName("[" + SERVICE_NAME + "/like] like 성공 테스트")
  @Test
  void likeSuccessTest() {
    var request = InterestFixtureFactory.createLikeRequest();
    var beforeSaved = InterestFixtureFactory.createBeforeSaved(request);
    var interest = InterestFixtureFactory.create(beforeSaved);

    given(saveInterestPort.save(any())).willReturn(interest);
    given(interestContentsExistHandlePort.isExistContents(
        interest.getInterestContentsType().getType(), interest.getContentsId())
    ).willReturn(true);

    var likedInterest = interestActionWriteService.like(request);

    Assertions.assertThat(likedInterest).isNotNull();
    Assertions.assertThat(likedInterest.id()).isNotNull();
    Assertions.assertThat(likedInterest.isLike()).isEqualTo(true);
    Assertions.assertThat(likedInterest.getClass()).isEqualTo(InterestDto.class);
    Assertions.assertThat(likedInterest).usingRecursiveComparison().isEqualTo(interest.toDto());
    then(saveInterestPort).should(times(1)).save(any());
    then(interestEventPort).should(times(1)).likeEvent(any());
    then(interestContentsExistHandlePort).should(times(1)).isExistContents(any(), any());
  }

  @DisplayName("[" + SERVICE_NAME + "/like] like 존재하지 않는 컨텐츠 실패 테스트")
  @Test
  void likeFailWithNotExistContentsTest() {
    var request = InterestFixtureFactory.createLikeRequest();

    given(interestContentsExistHandlePort.isExistContents(
        request.interestContentsType(), request.contentsId())
    ).willReturn(false);

    Assertions.assertThatThrownBy(() -> interestActionWriteService.like(request))
        .isInstanceOf(IllegalArgumentException.class).hasMessage("해당 컨텐츠가 없습니다.");
    then(saveInterestPort).should(times(0)).save(any());
    then(interestEventPort).should(times(0)).likeEvent(any());
    then(interestContentsExistHandlePort).should(times(1)).isExistContents(any(), any());
  }

  @DisplayName("[" + SERVICE_NAME + "/disLike] disLike 성공 테스트")
  @Test
  void disLikeSuccessTest() {
    var request = InterestFixtureFactory.createDislikeRequest();
    var beforeSaved = InterestFixtureFactory.createBeforeSaved(request);
    var interest = InterestFixtureFactory.create(beforeSaved);

    given(saveInterestPort.save(any())).willReturn(interest);
    given(interestContentsExistHandlePort.isExistContents(
        interest.getInterestContentsType().getType(), interest.getContentsId())
    ).willReturn(true);

    var disLikedInterest = interestActionWriteService.disLike(request);

    Assertions.assertThat(disLikedInterest).isNotNull();
    Assertions.assertThat(disLikedInterest.id()).isNotNull();
    Assertions.assertThat(disLikedInterest.isLike()).isEqualTo(false);
    Assertions.assertThat(disLikedInterest.getClass()).isEqualTo(InterestDto.class);
    Assertions.assertThat(disLikedInterest).usingRecursiveComparison().isEqualTo(interest.toDto());
    then(saveInterestPort).should(times(1)).save(any());
    then(interestEventPort).should(times(1)).dislikeEvent(any());
    then(interestContentsExistHandlePort).should(times(1)).isExistContents(any(), any());
  }

  @DisplayName("[" + SERVICE_NAME + "/dislike] dislike 존재하지 않는 컨텐츠 실패 테스트")
  @Test
  void dislikeFailWithNotExistContentsTest() {
    var request = InterestFixtureFactory.createDislikeRequest();

    given(interestContentsExistHandlePort.isExistContents(
        request.interestContentsType(), request.contentsId())
    ).willReturn(false);

    Assertions.assertThatThrownBy(() -> interestActionWriteService.disLike(request))
        .isInstanceOf(IllegalArgumentException.class).hasMessage("해당 컨텐츠가 없습니다.");
    then(saveInterestPort).should(times(0)).save(any());
    then(interestEventPort).should(times(0)).dislikeEvent(any());
    then(interestContentsExistHandlePort).should(times(1)).isExistContents(any(), any());
  }

  @DisplayName("[" + SERVICE_NAME + "/deleteInterest] deleteInterest like 삭제 성공 테스트")
  @Test
  void deleteLikeInterestSuccessTest() {
    var interest = InterestFixtureFactory.create();
    var id = interest.getId();

    given(loadInterestPort.load(id)).willReturn(interest);

    interestActionWriteService.deleteInterest(id);

    then(loadInterestPort).should(times(1)).load(id);
    then(saveInterestPort).should(times(1)).delete(any());
    then(interestEventPort).should(times(1)).deletedLikeEvent(any());
    then(interestEventPort).should(times(0)).deletedDislikeEvent(any());
  }

  @DisplayName("[" + SERVICE_NAME + "/deleteInterest] deleteInterest dislike 삭제 성공 테스트")
  @Test
  void deleteDislikeInterestSuccessTest() {
    var interest = InterestFixtureFactory.create(false);
    var id = interest.getId();

    given(loadInterestPort.load(id)).willReturn(interest);

    interestActionWriteService.deleteInterest(id);

    then(loadInterestPort).should(times(1)).load(id);
    then(saveInterestPort).should(times(1)).delete(any());
    then(interestEventPort).should(times(0)).deletedLikeEvent(any());
    then(interestEventPort).should(times(1)).deletedDislikeEvent(any());
  }
}