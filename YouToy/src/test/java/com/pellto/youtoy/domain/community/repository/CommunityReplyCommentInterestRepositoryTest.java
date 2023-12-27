package com.pellto.youtoy.domain.community.repository;

import com.pellto.youtoy.domain.community.domain.CommunityComment;
import com.pellto.youtoy.domain.community.domain.CommunityPost;
import com.pellto.youtoy.domain.community.domain.PostReplyComment;
import com.pellto.youtoy.domain.community.util.CommunityCommentFactory;
import com.pellto.youtoy.domain.community.util.CommunityPostFactory;
import com.pellto.youtoy.domain.community.util.CommunityReplyCommentInterestFactory;
import com.pellto.youtoy.domain.community.util.PostReplyCommentFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Tag("repository")
@ExtendWith(SpringExtension.class)
@DataJpaTest
class CommunityReplyCommentInterestRepositoryTest {

  @Autowired
  private CommunityReplyCommentInterestRepository replyCommentInterestRepository;

  @Autowired
  private CommunityPostRepository postRepository;
  @Autowired
  private CommunityCommentRepository commentRepository;
  @Autowired
  private PostReplyRepository postReplyRepository;

  private CommunityPost postSetting() {
    var post = CommunityPostFactory.createPost();
    return postRepository.save(post);
  }

  private CommunityComment commentSetting() {
    var post = postSetting();
    var comment = CommunityCommentFactory.createCommunityComment(post);
    return commentRepository.save(comment);
  }

  private PostReplyComment replySetting() {
    var comment = commentSetting();
    var reply = PostReplyCommentFactory.createReplyComment(comment);
    return postReplyRepository.save(reply);
  }

  @DisplayName("[replyCommentInterestRepository: save: success] 답글 관심 저장 성공 테스트")
  @Test
  void saveSuccessTest() {
    var replyCommentInterest = CommunityReplyCommentInterestFactory.createBeforeSavedCommentInterest();

    var savedCommentInterest = replyCommentInterestRepository.save(replyCommentInterest);

    Assertions.assertThat(savedCommentInterest).isNotNull();
    Assertions.assertThat(savedCommentInterest.getId()).isNotNull();
    Assertions.assertThat(savedCommentInterest.getCreatedAt()).isNotNull();
  }

  @DisplayName("[replyCommentInterestRepository: findByCommentIdAndUserUuid: success] 답글 관심 commentId & uuid 조회 성공 테스트")
  @Test
  void findByCommentIdAndUserUuidSuccessTest() {
    var replyComment = replySetting();
    var replyCommentInterest = CommunityReplyCommentInterestFactory.createInterest(replyComment);
    replyCommentInterestRepository.save(replyCommentInterest);

    var nullableReplyCommentInterest = replyCommentInterestRepository
        .findByInterestedReplyCommentAndInterestingUserUuid(replyComment,
            replyCommentInterest.getInterestingUserUuid());

    Assertions.assertThat(nullableReplyCommentInterest).isNotNull();
    Assertions.assertThat(nullableReplyCommentInterest).isNotEmpty();
    var foundReplyCommentInterest = nullableReplyCommentInterest.get();
    Assertions.assertThat(foundReplyCommentInterest.getId())
        .isEqualTo(replyCommentInterest.getId());
    Assertions.assertThat(foundReplyCommentInterest.getInterestedReplyComment())
        .isEqualTo(replyCommentInterest.getInterestedReplyComment());
    Assertions.assertThat(foundReplyCommentInterest.getInterestingUserUuid().getValue())
        .isEqualTo(replyCommentInterest.getInterestingUserUuid().getValue());
    Assertions.assertThat(foundReplyCommentInterest.getCreatedAt())
        .isEqualTo(replyCommentInterest.getCreatedAt());
    Assertions.assertThat(foundReplyCommentInterest.isDislike())
        .isEqualTo(replyCommentInterest.isDislike());
  }

  @DisplayName("[replyCommentInterestRepository: findAllByInterestedCommunityComment: success] 답글 관심 commentId 전체 조회 성공 테스트")
  @Test
  void findAllByInterestedCommunityCommentSuccessTest() {
    var replyComment = replySetting();
    var replyCommentInterest = CommunityReplyCommentInterestFactory.createInterest(replyComment);
    replyCommentInterestRepository.save(replyCommentInterest);

    var replyCommentInterests = replyCommentInterestRepository.findAll();

    Assertions.assertThat(replyCommentInterests).isNotNull();
    Assertions.assertThat(replyCommentInterests).isNotEmpty();
    Assertions.assertThat(replyCommentInterests.size()).isEqualTo(1);
  }

  @DisplayName("[replyCommentInterestRepository: deleteById: success] 답글 관심 삭제 성공 테스트")
  @Test
  void deleteByIdSuccessTest() {
    var replyComment = replySetting();
    var replyCommentInterest = CommunityReplyCommentInterestFactory.createInterest(replyComment);
    replyCommentInterestRepository.save(replyCommentInterest);
    var foundReplyCommentInterest = replyCommentInterestRepository
        .findByInterestedReplyCommentAndInterestingUserUuid(replyComment,
            replyCommentInterest.getInterestingUserUuid())
        .orElse(null);
    assert foundReplyCommentInterest != null;

    replyCommentInterestRepository.deleteById(foundReplyCommentInterest.getId());

    var deletedCommentInterest = replyCommentInterestRepository
        .findByInterestedReplyCommentAndInterestingUserUuid(
            replyComment,
            foundReplyCommentInterest.getInterestingUserUuid()
        );
    Assertions.assertThat(deletedCommentInterest).isEmpty();
  }
}