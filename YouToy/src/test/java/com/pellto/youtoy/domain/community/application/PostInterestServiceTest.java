package com.pellto.youtoy.domain.community.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.domain.community.domain.PostInterest;
import com.pellto.youtoy.domain.community.dto.PostInterestDto;
import com.pellto.youtoy.domain.community.exception.NotExistPostInterestException;
import com.pellto.youtoy.domain.community.repository.CommunityPostInterestRepository;
import com.pellto.youtoy.domain.community.util.CommunityPostFactory;
import com.pellto.youtoy.domain.community.util.PostInterestFactory;
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
class PostInterestServiceTest {

  @InjectMocks
  private PostInterestService postInterestService;

  @Mock
  private CommunityPostInterestRepository postInterestRepository;
  @Mock
  private CommentPostReadService postReadService;

  @DisplayName("[postInterestService: interest: success] 게시글 관심 성공 테스트")
  @Test
  void interestSuccessTest() {
    var req = PostInterestFactory.createInterestPostRequest();
    var post = CommunityPostFactory.createPost();
    var userUuid = new UserUUID(req.userUuid());
    var postInterest = PostInterestFactory.createInterest(post, userUuid);

    given(postReadService.getById(any())).willReturn(post);
    given(postInterestRepository.save(any())).willReturn(postInterest);

    var savedPostInterest = postInterestService.interest(post.getId(), req);

    then(postReadService).should(times(1)).getById(post.getId());
    then(postInterestRepository).should(times(1)).save(any());
    Assertions.assertThat(savedPostInterest).isNotNull();
    Assertions.assertThat(savedPostInterest.getClass()).isEqualTo(PostInterestDto.class);
  }

  @DisplayName("[postInterestService: findByPostIdAndUserUuid: success] 게시글 관심 postId & uuid 조회 성공 테스트")
  @Test
  void findByPostIdAndUserUuidSuccessTest() {
    var post = CommunityPostFactory.createPost();
    var postInterest = PostInterestFactory.createInterest(post);

    given(postReadService.getById(any())).willReturn(post);
    given(
        postInterestRepository.findByInterestedPostAndInterestingUserUuid(any(), any())
    ).willReturn(Optional.ofNullable(postInterest));

    var foundPostInterest = postInterestService
        .findByPostIdAndUserUuid(
            post.getId(),
            postInterest.getInterestingUserUuid().getValue()
        );

    then(postReadService).should(times(1)).getById(post.getId());
    then(postInterestRepository).should(times(1))
        .findByInterestedPostAndInterestingUserUuid(any(), any());
    Assertions.assertThat(foundPostInterest).isNotNull();
    Assertions.assertThat(foundPostInterest.getClass()).isEqualTo(PostInterestDto.class);
  }

  @DisplayName("[postInterestService: findByPostIdAndUserUuid: not exist post interest exception] 게시글 관심 postId & uuid 조회 실패 테스트")
  @Test
  void findByPostIdAndUserUuidFailWithNotExistPostInterestExceptionTest() {
    var post = CommunityPostFactory.createPost();
    var postInterest = PostInterestFactory.createInterest(post);

    given(postReadService.getById(any())).willReturn(post);
    given(
        postInterestRepository.findByInterestedPostAndInterestingUserUuid(any(), any())
    ).willReturn(Optional.empty());

    Assertions.assertThatThrownBy(
        () -> postInterestService
            .findByPostIdAndUserUuid(
                post.getId(),
                postInterest.getInterestingUserUuid().getValue()
            )
    ).isInstanceOf(NotExistPostInterestException.class);
    then(postReadService).should(times(1)).getById(post.getId());
    then(postInterestRepository).should(times(1))
        .findByInterestedPostAndInterestingUserUuid(any(), any());
  }

  @DisplayName("[postInterestService: findAllByPostId: success] 게시글 관심 postId 전체 조회 성공 테스트")
  @Test
  void findAllByPostIdSuccessTest() {
    var post = CommunityPostFactory.createPost();
    var postInterest = PostInterestFactory.createInterest(post);
    var postInterestList = new ArrayList<PostInterest>();
    postInterestList.add(postInterest);

    given(postReadService.getById(any())).willReturn(post);
    given(
        postInterestRepository.findAllByInterestedPost(any())
    ).willReturn(postInterestList);

    var foundPostInterests = postInterestService
        .findAllByPostId(post.getId());

    then(postReadService).should(times(1)).getById(post.getId());
    then(postInterestRepository).should(times(1)).findAllByInterestedPost(any());
    Assertions.assertThat(foundPostInterests).isNotEmpty();
    Assertions.assertThat(foundPostInterests.get(0).getClass()).isEqualTo(PostInterestDto.class);
  }

  @DisplayName("[postInterestService: deleteInterestByPostIdAndUserUuid: success] 게시글 관심 postId & uuid 조건 삭제 성공 테스트")
  @Test
  void deleteInterestByPostIdAndUserUuidSuccessTest() {
    var post = CommunityPostFactory.createPost();
    var postInterest = PostInterestFactory.createInterest(post);

    given(postReadService.getById(any())).willReturn(post);
    given(postInterestRepository
        .findByInterestedPostAndInterestingUserUuid(any(), any())).willReturn(
        Optional.ofNullable(postInterest));

    postInterestService
        .deleteInterestByPostIdAndUserUuid(post.getId(),
            postInterest.getInterestingUserUuid().getValue()
        );

    then(postReadService).should(times(1)).getById(post.getId());
    then(postInterestRepository).should(times(1))
        .findByInterestedPostAndInterestingUserUuid(any(), any());
    then(postInterestRepository).should(times(1)).deleteById(any());
  }

  @DisplayName("[postInterestService: deleteInterestByPostIdAndUserUuid: not exist post interest exception] 게시글 관심 postId & uuid 조건 삭제 실패 테스트")
  @Test
  void deleteInterestByPostIdAndUserUuidFailWithNotExistPostInterestExceptionTest() {
    var post = CommunityPostFactory.createPost();
    var postInterest = PostInterestFactory.createInterest(post);

    given(postReadService.getById(any())).willReturn(post);
    given(postInterestRepository
        .findByInterestedPostAndInterestingUserUuid(any(), any())).willReturn(Optional.empty());

    Assertions.assertThatThrownBy(
        () -> postInterestService
            .deleteInterestByPostIdAndUserUuid(post.getId(),
                postInterest.getInterestingUserUuid().getValue()
            )
    ).isInstanceOf(NotExistPostInterestException.class);
    then(postReadService).should(times(1)).getById(post.getId());
    then(postInterestRepository).should(times(1))
        .findByInterestedPostAndInterestingUserUuid(any(), any());
    then(postInterestRepository).should(times(0)).deleteById(any());
  }
}