package com.pellto.youtoy.domain.video.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.domain.user.domain.UserUUID;
import com.pellto.youtoy.domain.video.domain.VideoInterest;
import com.pellto.youtoy.domain.video.dto.VideoInterestDto;
import com.pellto.youtoy.domain.video.repository.VideoInterestRepository;
import com.pellto.youtoy.domain.video.util.VideoFactory;
import com.pellto.youtoy.domain.video.util.VideoInterestFactory;
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
class VideoInterestServiceTest {

  private static final String TEST_NAME = "VideoInterestService";

  @InjectMocks
  private VideoInterestService videoInterestService;

  @Mock
  private VideoInterestRepository videoInterestRepository;

  @Mock
  private VideoReadService contentsReadService;

  @DisplayName("[" + TEST_NAME + ": write: success] 관심 성공 테스트")
  @Test
  void writeSuccessTest() {
    var req = VideoInterestFactory.createWriteInterestRequest();
    var contents = VideoFactory.create(req.contentsId());
    var userUuid = new UserUUID(req.interestingUserUuid());
    var interest = VideoInterestFactory.create(contents, userUuid);

    given(contentsReadService.getById(any())).willReturn(contents);
    given(videoInterestRepository.save(any())).willReturn(interest);

    var savedInterest = videoInterestService.write(req);

    then(contentsReadService).should(times(1)).getById(contents.getId());
    then(videoInterestRepository).should(times(1)).save(any());
    Assertions.assertThat(savedInterest).isNotNull();
    Assertions.assertThat(savedInterest.getClass()).isEqualTo(VideoInterestDto.class);
  }

  @DisplayName("[" + TEST_NAME + ": findAllByContentsId: success] 관심 contents id 조건 전체 조회 성공 테스트")
  @Test
  void findAllByContentsIdSuccessTest() {
    var contents = VideoFactory.create();
    var interest = VideoInterestFactory.create(contents);
    var videoInterestList = new ArrayList<VideoInterest>();
    videoInterestList.add(interest);

    given(contentsReadService.getById(any())).willReturn(contents);
    given(
        videoInterestRepository.findAllByInterestedContents(any())
    ).willReturn(videoInterestList);

    var foundInterestList = videoInterestService
        .findAllByContentsId(contents.getId());

    then(contentsReadService).should(times(1)).getById(contents.getId());
    then(videoInterestRepository).should(times(1)).findAllByInterestedContents(any());
    Assertions.assertThat(foundInterestList).isNotEmpty();
    Assertions.assertThat(foundInterestList.get(0).getClass()).isEqualTo(VideoInterestDto.class);
  }

  @DisplayName("[" + TEST_NAME + ": deleteById: success] 관심 id 조건 삭제 성공 테스트")
  @Test
  void deleteByIdSuccessTest() {
    var id = 1L;

    videoInterestService.deleteById(id);

    then(videoInterestRepository).should(times(1)).deleteById(id);
  }
}