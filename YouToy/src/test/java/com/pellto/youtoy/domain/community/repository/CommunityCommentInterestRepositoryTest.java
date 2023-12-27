package com.pellto.youtoy.domain.community.repository;

import com.pellto.youtoy.domain.community.domain.CommunityComment;
import com.pellto.youtoy.domain.community.domain.CommunityPost;
import com.pellto.youtoy.domain.community.util.CommunityCommentFactory;
import com.pellto.youtoy.domain.community.util.CommunityCommentInterestFactory;
import com.pellto.youtoy.domain.community.util.CommunityPostFactory;
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
class CommunityCommentInterestRepositoryTest {

  @Autowired
  private CommunityCommentInterestRepository commentInterestRepository;

  @Autowired
  private CommunityPostRepository postRepository;
  @Autowired
  private CommunityCommentRepository commentRepository;

  private CommunityPost postSetting() {
    var post = CommunityPostFactory.createPost();
    return postRepository.save(post);
  }

  private CommunityComment commentSetting() {
    var post = postSetting();
    var comment = CommunityCommentFactory.createCommunityComment(post);
    return commentRepository.save(comment);
  }

  @DisplayName("[commentInterestRepository: save: success] 댓글 관심 저장 성공 테스트")
  @Test
  void saveSuccessTest() {
    var commentInterest = CommunityCommentInterestFactory.createBeforeSavedCommentInterest();

    var savedCommentInterest = commentInterestRepository.save(commentInterest);

    Assertions.assertThat(savedCommentInterest).isNotNull();
    Assertions.assertThat(savedCommentInterest.getId()).isNotNull();
    Assertions.assertThat(savedCommentInterest.getCreatedAt()).isNotNull();
  }

  @DisplayName("[commentInterestRepository: findByCommentIdAndUserUuid: success] 댓글 관심 commentId & uuid 조회 성공 테스트")
  @Test
  void findByCommentIdAndUserUuidSuccessTest() {
    var comment = commentSetting();
    var commentInterest = CommunityCommentInterestFactory.createInterest(comment);
    commentInterestRepository.save(commentInterest);

    var nullableCommentInterest = commentInterestRepository
        .findByInterestedCommunityCommentAndInterestingUserUuid(comment,
            commentInterest.getInterestingUserUuid());

    Assertions.assertThat(nullableCommentInterest).isNotNull();
    Assertions.assertThat(nullableCommentInterest).isNotEmpty();
    var foundCommentInterest = nullableCommentInterest.get();
    Assertions.assertThat(foundCommentInterest.getId()).isEqualTo(commentInterest.getId());
    Assertions.assertThat(foundCommentInterest.getInterestedCommunityComment())
        .isEqualTo(commentInterest.getInterestedCommunityComment());
    Assertions.assertThat(foundCommentInterest.getInterestingUserUuid().getValue())
        .isEqualTo(commentInterest.getInterestingUserUuid().getValue());
    Assertions.assertThat(foundCommentInterest.getCreatedAt())
        .isEqualTo(commentInterest.getCreatedAt());
    Assertions.assertThat(foundCommentInterest.isDislike()).isEqualTo(commentInterest.isDislike());
  }

  @DisplayName("[commentInterestRepository: findAllByInterestedCommunityComment: success] 댓글 관심 commentId 전체 조회 성공 테스트")
  @Test
  void findAllByInterestedCommunityCommentSuccessTest() {
    var comment = commentSetting();
    var commentInterest = CommunityCommentInterestFactory.createInterest(comment);
    commentInterestRepository.save(commentInterest);

    var commentInterests = commentInterestRepository.findAllByInterestedCommunityComment(comment);

    Assertions.assertThat(commentInterests).isNotNull();
    Assertions.assertThat(commentInterests).isNotEmpty();
    Assertions.assertThat(commentInterests.size()).isEqualTo(1);
  }

  @DisplayName("[commentInterestRepository: deleteById: success] 댓글 관심 삭제 성공 테스트")
  @Test
  void deleteByIdSuccessTest() {
    var comment = commentSetting();
    var commentInterest = CommunityCommentInterestFactory.createInterest(comment);
    commentInterestRepository.save(commentInterest);
    var foundCommentInterest = commentInterestRepository
        .findByInterestedCommunityCommentAndInterestingUserUuid(comment,
            commentInterest.getInterestingUserUuid())
        .orElse(null);
    assert foundCommentInterest != null;

    commentInterestRepository.deleteById(foundCommentInterest.getId());

    var deletedCommentInterest = commentInterestRepository
        .findByInterestedCommunityCommentAndInterestingUserUuid(
            comment,
            foundCommentInterest.getInterestingUserUuid()
        );
    Assertions.assertThat(deletedCommentInterest).isEmpty();
  }
}