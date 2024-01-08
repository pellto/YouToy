package com.pellto.youtoy.domain.video.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.domain.user.domain.UserUUID;
import com.pellto.youtoy.domain.video.domain.VideoCommentInterest;
import com.pellto.youtoy.domain.video.dto.VideoCommentInterestDto;
import com.pellto.youtoy.domain.video.repository.VideoCommentInterestRepository;
import com.pellto.youtoy.domain.video.util.VideoCommentFactory;
import com.pellto.youtoy.domain.video.util.VideoCommentInterestFactory;
import java.util.ArrayList;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@Tag("service")
@ExtendWith(MockitoExtension.class)
class VideoCommentInterestServiceTest {

  private static final String TEST_NAME = "VideoCommentInterestService";

  @InjectMocks
  private VideoCommentInterestService interestService;

  @Mock
  private VideoCommentInterestRepository interestRepository;

  @Mock
  private VideoCommentReadService contentsReadService;

  @DisplayName("[" + TEST_NAME + ": write: success] 관심 성공 테스트")
  @Test
  void writeSuccessTest() {
    var req = VideoCommentInterestFactory.createWriteInterestRequest();
    var contents = VideoCommentFactory.create(req.contentsId());
    var userUuid = new UserUUID(req.interestingUserUuid());
    var interest = VideoCommentInterestFactory.create(contents, userUuid);

    given(contentsReadService.getById(any())).willReturn(contents);
    given(interestRepository.save(any())).willReturn(interest);

    var savedInterest = interestService.write(req);

    then(contentsReadService).should(times(1)).getById(contents.getId());
    then(interestRepository).should(times(1)).save(any());
    Assertions.assertThat(savedInterest).isNotNull();
    Assertions.assertThat(savedInterest.getClass()).isEqualTo(VideoCommentInterestDto.class);
  }

  @DisplayName(
      "[" + TEST_NAME + ": findAllByInterestedCommentId: success] 관심 contents id 조건 전체 조회 성공 테스트")
  @Test
  void findAllByInterestedCommentIdSuccessTest() {
    var contents = VideoCommentFactory.create();
    var interest = VideoCommentInterestFactory.create(contents);
    var videoInterestList = new ArrayList<VideoCommentInterest>();
    videoInterestList.add(interest);

    given(contentsReadService.getById(any())).willReturn(contents);
    given(
        interestRepository.findAllByInterestedComment(any())
    ).willReturn(videoInterestList);

    var foundInterestList = interestService
        .findAllByInterestedCommentId(contents.getId());

    then(contentsReadService).should(times(1)).getById(contents.getId());
    then(interestRepository).should(times(1)).findAllByInterestedComment(any());
    Assertions.assertThat(foundInterestList).isNotEmpty();
    Assertions.assertThat(foundInterestList.get(0).getClass())
        .isEqualTo(VideoCommentInterestDto.class);
  }

  @DisplayName("[" + TEST_NAME + ": deleteById: success] 관심 id 조건 삭제 성공 테스트")
  @Test
  void deleteByIdSuccessTest() {
    var id = 1L;

    interestService.deleteById(id);

    then(interestRepository).should(times(1)).deleteById(id);
  }
}