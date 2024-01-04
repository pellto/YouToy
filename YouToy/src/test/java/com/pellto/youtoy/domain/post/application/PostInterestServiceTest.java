package com.pellto.youtoy.domain.post.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.domain.post.domain.PostInterest;
import com.pellto.youtoy.domain.post.dto.PostInterestDto;
import com.pellto.youtoy.domain.post.repository.PostInterestRepository;
import com.pellto.youtoy.domain.post.util.CommunityPostFactory;
import com.pellto.youtoy.domain.post.util.PostInterestFactory;
import com.pellto.youtoy.domain.user.domain.UserUUID;
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
class PostInterestServiceTest {

  @InjectMocks
  private PostInterestService postInterestService;

  @Mock
  private PostInterestRepository postInterestRepository;
  @Mock
  private PostReadService postReadService;

  @DisplayName("[postInterestService: interest: success] 게시글 관심 성공 테스트")
  @Test
  void interestSuccessTest() {
    var req = PostInterestFactory.createInterestPostRequest();
    var post = CommunityPostFactory.createPost();
    var userUuid = new UserUUID(req.interestingUserUuid());
    var postInterest = PostInterestFactory.createInterest(post, userUuid);

    given(postReadService.getById(any())).willReturn(post);
    given(postInterestRepository.save(any())).willReturn(postInterest);

    var savedPostInterest = postInterestService.write(req);

    then(postReadService).should(times(1)).getById(post.getId());
    then(postInterestRepository).should(times(1)).save(any());
    Assertions.assertThat(savedPostInterest).isNotNull();
    Assertions.assertThat(savedPostInterest.getClass()).isEqualTo(PostInterestDto.class);
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
        postInterestRepository.findAllByInterestedContents(any())
    ).willReturn(postInterestList);

    var foundPostInterests = postInterestService
        .findAllByContentsId(post.getId());

    then(postReadService).should(times(1)).getById(post.getId());
    then(postInterestRepository).should(times(1)).findAllByInterestedContents(any());
    Assertions.assertThat(foundPostInterests).isNotEmpty();
    Assertions.assertThat(foundPostInterests.get(0).getClass()).isEqualTo(PostInterestDto.class);
  }

}