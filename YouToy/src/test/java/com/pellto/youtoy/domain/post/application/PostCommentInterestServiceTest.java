package com.pellto.youtoy.domain.post.application;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import com.pellto.youtoy.domain.post.domain.PostCommentInterest;
import com.pellto.youtoy.domain.post.dto.PostCommentInterestDto;
import com.pellto.youtoy.domain.post.repository.PostCommentInterestRepository;
import com.pellto.youtoy.domain.post.util.PostCommentFactory;
import com.pellto.youtoy.domain.post.util.PostCommentInterestFactory;
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
class PostCommentInterestServiceTest {

  @InjectMocks
  private PostCommentInterestService commentInterestService;

  @Mock
  private PostCommentInterestRepository commentInterestRepository;
  @Mock
  private PostCommentReadService commentReadService;

  @DisplayName("[commentInterestService: interest: success] 댓글 관심 성공 테스트")
  @Test
  void interestSuccessTest() {
    var req = PostCommentInterestFactory.createInterestRequest();
    var comment = PostCommentFactory.createCommunityComment();
    var userUuid = new UserUUID(req.interestingUserUuid());
    var commentInterest = PostCommentInterestFactory.createInterest(comment, userUuid);

    given(commentReadService.getById(any())).willReturn(comment);
    given(commentInterestRepository.save(any())).willReturn(commentInterest);

    var savedCommentDto = commentInterestService.write(req);

    then(commentReadService).should(times(1)).getById(comment.getId());
    then(commentInterestRepository).should(times(1)).save(any());
    Assertions.assertThat(savedCommentDto).isNotNull();
    Assertions.assertThat(savedCommentDto.getClass())
        .isEqualTo(PostCommentInterestDto.class);
  }

  @DisplayName("[commentInterestService: findAll: success] 댓글 관심 전체 조회 성공 테스트")
  @Test
  void findAllByCommentIdSuccessTest() {
    var comment = PostCommentFactory.createCommunityComment();
    var commentInterest = PostCommentInterestFactory.createInterest(comment);
    var commentInterestList = new ArrayList<PostCommentInterest>();
    commentInterestList.add(commentInterest);

    given(
        commentInterestRepository.findAll()
    ).willReturn(commentInterestList);

    var foundCommentInterests = commentInterestService.findAll();

    then(commentInterestRepository).should(times(1)).findAll();
    Assertions.assertThat(foundCommentInterests).isNotEmpty();
    Assertions.assertThat(foundCommentInterests.get(0).getClass())
        .isEqualTo(PostCommentInterestDto.class);
  }
}