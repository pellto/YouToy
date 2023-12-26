package com.pellto.youtoy.domain.community.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.domain.community.domain.CommunityCommentInterest;
import com.pellto.youtoy.domain.community.dto.CommunityCommentInterestDto;
import com.pellto.youtoy.domain.community.exception.NotExistCommentInterestException;
import com.pellto.youtoy.domain.community.repository.CommunityCommentInterestRepository;
import com.pellto.youtoy.domain.community.util.CommunityCommentFactory;
import com.pellto.youtoy.domain.community.util.CommunityCommentInterestFactory;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import java.util.ArrayList;
import java.util.Optional;
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
class CommunityCommentInterestServiceTest {

  @InjectMocks
  private CommunityCommentInterestService commentInterestService;

  @Mock
  private CommunityCommentInterestRepository commentInterestRepository;
  @Mock
  private CommunityCommentReadService commentReadService;

  @DisplayName("[commentInterestService: interest: success] 댓글 관심 성공 테스트")
  @Test
  void interestSuccessTest() {
    var req = CommunityCommentInterestFactory.createInterestRequest();
    var comment = CommunityCommentFactory.createCommunityComment();
    var userUuid = new UserUUID(req.userUuid());
    var commentInterest = CommunityCommentInterestFactory.createInterest(comment, userUuid);

    given(commentReadService.getById(any())).willReturn(comment);
    given(commentInterestRepository.save(any())).willReturn(commentInterest);

    var savedCommentDto = commentInterestService.interest(comment.getId(), req);

    then(commentReadService).should(times(1)).getById(comment.getId());
    then(commentInterestRepository).should(times(1)).save(any());
    Assertions.assertThat(savedCommentDto).isNotNull();
    Assertions.assertThat(savedCommentDto.getClass())
        .isEqualTo(CommunityCommentInterestDto.class);
  }

  @DisplayName("[commentInterestService: findByCommentIdAndUserUuid: success] 댓글 관심 commentId & uuid 조회 성공 테스트")
  @Test
  void findByCommentIdAndUserUuidSuccessTest() {
    var comment = CommunityCommentFactory.createCommunityComment();
    var commentInterest = CommunityCommentInterestFactory.createInterest(comment);

    given(commentReadService.getById(any())).willReturn(comment);
    given(
        commentInterestRepository.findByInterestedCommunityCommentAndInterestingUserUuid(any(),
            any())
    ).willReturn(Optional.ofNullable(commentInterest));

    var foundCommentInterest = commentInterestService
        .findByCommentIdAndUserUuid(
            comment.getId(),
            commentInterest.getInterestingUserUuid().getValue()
        );

    then(commentReadService).should(times(1)).getById(comment.getId());
    then(commentInterestRepository).should(times(1))
        .findByInterestedCommunityCommentAndInterestingUserUuid(any(), any());
    Assertions.assertThat(foundCommentInterest).isNotNull();
    Assertions.assertThat(foundCommentInterest.getClass())
        .isEqualTo(CommunityCommentInterestDto.class);
  }

  @DisplayName("[commentInterestService: findByCommentIdAndUserUuid: not exist comment interest exception] 댓글 관심 commentId & uuid 조회 실패 테스트")
  @Test
  void findByCommentIdAndUserUuidFailWithNotExistCommentInterestExceptionTest() {
    var comment = CommunityCommentFactory.createCommunityComment();
    var commentInterest = CommunityCommentInterestFactory.createInterest(comment);

    given(commentReadService.getById(any())).willReturn(comment);
    given(
        commentInterestRepository.findByInterestedCommunityCommentAndInterestingUserUuid(any(),
            any())
    ).willReturn(Optional.empty());

    Assertions.assertThatThrownBy(
        () -> commentInterestService
            .findByCommentIdAndUserUuid(
                comment.getId(),
                commentInterest.getInterestingUserUuid().getValue()
            )
    ).isInstanceOf(NotExistCommentInterestException.class);
    then(commentReadService).should(times(1)).getById(comment.getId());
    then(commentInterestRepository).should(times(1))
        .findByInterestedCommunityCommentAndInterestingUserUuid(any(), any());
  }

  @DisplayName("[commentInterestService: findAll: success] 댓글 관심 전체 조회 성공 테스트")
  @Test
  void findAllByCommentIdSuccessTest() {
    var comment = CommunityCommentFactory.createCommunityComment();
    var commentInterest = CommunityCommentInterestFactory.createInterest(comment);
    var commentInterestList = new ArrayList<CommunityCommentInterest>();
    commentInterestList.add(commentInterest);

    given(
        commentInterestRepository.findAll()
    ).willReturn(commentInterestList);

    var foundCommentInterests = commentInterestService.findAll();

    then(commentInterestRepository).should(times(1)).findAll();
    Assertions.assertThat(foundCommentInterests).isNotEmpty();
    Assertions.assertThat(foundCommentInterests.get(0).getClass())
        .isEqualTo(CommunityCommentInterestDto.class);
  }

  @DisplayName("[commentInterestService: deleteInterestByCommunityCommentIdAndUserUuid: success] 댓글 관심 commentId & uuid 조건 삭제 성공 테스트")
  @Test
  void deleteInterestByCommunityCommentIdAndUserUuidSuccessTest() {
    var comment = CommunityCommentFactory.createCommunityComment();
    var commentInterest = CommunityCommentInterestFactory.createInterest(comment);

    given(commentReadService.getById(any())).willReturn(comment);
    given(commentInterestRepository
        .findByInterestedCommunityCommentAndInterestingUserUuid(any(), any())).willReturn(
        Optional.ofNullable(commentInterest));

    commentInterestService
        .deleteInterestByCommunityCommentIdAndUserUuid(comment.getId(),
            commentInterest.getInterestingUserUuid().getValue()
        );

    then(commentReadService).should(times(1)).getById(comment.getId());
    then(commentInterestRepository).should(times(1))
        .findByInterestedCommunityCommentAndInterestingUserUuid(any(), any());
    then(commentInterestRepository).should(times(1)).deleteById(any());
  }

  @DisplayName("[commentInterestService: deleteInterestByCommunityCommentIdAndUserUuid: not exist comment interest exception] 댓글 관심 commentId & uuid 조건 삭제 실패 테스트")
  @Test
  void deleteInterestByCommunityCommentIdAndUserUuidFailWithNotExistCommentInterestExceptionTest() {
    var comment = CommunityCommentFactory.createCommunityComment();
    var commentInterest = CommunityCommentInterestFactory.createInterest(comment);

    given(commentReadService.getById(any())).willReturn(comment);
    given(commentInterestRepository
        .findByInterestedCommunityCommentAndInterestingUserUuid(any(), any())).willReturn(
        Optional.empty());

    Assertions.assertThatThrownBy(
        () -> commentInterestService
            .deleteInterestByCommunityCommentIdAndUserUuid(comment.getId(),
                commentInterest.getInterestingUserUuid().getValue()
            )
    ).isInstanceOf(NotExistCommentInterestException.class);
    then(commentReadService).should(times(1)).getById(comment.getId());
    then(commentInterestRepository).should(times(1))
        .findByInterestedCommunityCommentAndInterestingUserUuid(any(), any());
    then(commentInterestRepository).should(times(0)).deleteById(any());
  }
}