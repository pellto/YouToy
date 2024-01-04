package com.pellto.youtoy.domain.video.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.domain.user.domain.UserUUID;
import com.pellto.youtoy.domain.video.domain.VideoReplyCommentInterest;
import com.pellto.youtoy.domain.video.dto.VideoReplyInterestDto;
import com.pellto.youtoy.domain.video.repository.VideoReplyInterestRepository;
import com.pellto.youtoy.domain.video.util.VideoReplyFactory;
import com.pellto.youtoy.domain.video.util.VideoReplyInterestFactory;
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
class VideoReplyInterestServiceTest {

  private static final String TEST_NAME = "VideoReplyInterestService";

  @InjectMocks
  private VideoReplyInterestService interestService;

  @Mock
  private VideoReplyInterestRepository interestRepository;

  @Mock
  private VideoReplyReadService contentsReadService;

  @DisplayName("[" + TEST_NAME + ": write: success] 관심 성공 테스트")
  @Test
  void writeSuccessTest() {
    var req = VideoReplyInterestFactory.createWriteInterestRequest();
    var contents = VideoReplyFactory.create(req.contentsId());
    var userUuid = new UserUUID(req.interestingUserUuid());
    var interest = VideoReplyInterestFactory.create(contents, userUuid);

    given(contentsReadService.getById(any())).willReturn(contents);
    given(interestRepository.save(any())).willReturn(interest);

    var savedInterest = interestService.write(req);

    then(contentsReadService).should(times(1)).getById(contents.getId());
    then(interestRepository).should(times(1)).save(any());
    Assertions.assertThat(savedInterest).isNotNull();
    Assertions.assertThat(savedInterest.getClass()).isEqualTo(VideoReplyInterestDto.class);
  }

  @DisplayName(
      "[" + TEST_NAME + ": findAllByInterestedReplyId: success] 관심 contents id 조건 전체 조회 성공 테스트")
  @Test
  void findAllByInterestedReplyIdSuccessTest() {
    var contents = VideoReplyFactory.create();
    var interest = VideoReplyInterestFactory.create(contents);
    var videoInterestList = new ArrayList<VideoReplyCommentInterest>();
    videoInterestList.add(interest);

    given(contentsReadService.getById(any())).willReturn(contents);
    given(
        interestRepository.findAllByInterestedReplyComment(any())
    ).willReturn(videoInterestList);

    var foundInterestList = interestService
        .findAllByInterestedReplyId(contents.getId());

    then(contentsReadService).should(times(1)).getById(contents.getId());
    then(interestRepository).should(times(1)).findAllByInterestedReplyComment(any());
    Assertions.assertThat(foundInterestList).isNotEmpty();
    Assertions.assertThat(foundInterestList.get(0).getClass())
        .isEqualTo(VideoReplyInterestDto.class);
  }

  @DisplayName("[" + TEST_NAME + ": deleteById: success] 관심 id 조건 삭제 성공 테스트")
  @Test
  void deleteByIdSuccessTest() {
    var id = 1L;

    interestService.deleteById(id);

    then(interestRepository).should(times(1)).deleteById(id);
  }
}