package com.pellto.youtoy.domain.community.repository;

import com.pellto.youtoy.domain.community.domain.CommunityPost;
import com.pellto.youtoy.domain.community.util.CommunityPostFactory;
import com.pellto.youtoy.domain.community.util.PostInterestFactory;
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
class CommunityPostInterestRepositoryTest {

  @Autowired
  private CommunityPostInterestRepository postInterestRepository;

  @Autowired
  private CommunityPostRepository postRepository;

  private CommunityPost postSetting() {
    var post = CommunityPostFactory.createPost();
    return postRepository.save(post);
  }

  @DisplayName("[postInterestRepository: save: success] 포스트 관심 저장 성공 테스트")
  @Test
  void saveSuccessTest() {
    var postInterest = PostInterestFactory.createBeforeSavedPostInterest();

    var savedPostInterest = postInterestRepository.save(postInterest);

    Assertions.assertThat(savedPostInterest).isNotNull();
    Assertions.assertThat(savedPostInterest.getId()).isNotNull();
    Assertions.assertThat(savedPostInterest.getCreatedAt()).isNotNull();
  }

  @DisplayName("[postInterestRepository: findByPostIdAndUserUuid: success] 포스트 관심 postId & uuid 조회 성공 테스트")
  @Test
  void findByPostIdAndUserUuidSuccessTest() {
    var post = postSetting();
    var postInterest = PostInterestFactory.createInterest(post);
    postInterestRepository.save(postInterest);

    var nullablePostInterest = postInterestRepository
        .findByInterestedPostAndInterestingUserUuid(post, postInterest.getInterestingUserUuid());

    Assertions.assertThat(nullablePostInterest).isNotNull();
    Assertions.assertThat(nullablePostInterest).isNotEmpty();
    var foundPostInterest = nullablePostInterest.get();
    Assertions.assertThat(foundPostInterest.getId()).isEqualTo(postInterest.getId());
    Assertions.assertThat(foundPostInterest.getInterestedPost())
        .isEqualTo(postInterest.getInterestedPost());
    Assertions.assertThat(foundPostInterest.getInterestingUserUuid().getValue())
        .isEqualTo(postInterest.getInterestingUserUuid().getValue());
    Assertions.assertThat(foundPostInterest.getCreatedAt()).isEqualTo(postInterest.getCreatedAt());
    Assertions.assertThat(foundPostInterest.isDislike()).isEqualTo(postInterest.isDislike());
  }

  @DisplayName("[postInterestRepository: findAllByInterestedPost: success] 포스트 관심 postId 전체 조회 성공 테스트")
  @Test
  void findAllByInterestedPostSuccessTest() {
    var post = postSetting();
    var postInterest = PostInterestFactory.createInterest(post);
    postInterestRepository.save(postInterest);

    var postInterests = postInterestRepository.findAllByInterestedPost(post);

    Assertions.assertThat(postInterests).isNotNull();
    Assertions.assertThat(postInterests).isNotEmpty();
    Assertions.assertThat(postInterests.size()).isEqualTo(1);
  }

  @DisplayName("[postInterestRepository: deleteById: success] 포스트 관심 삭제 성공 테스트")
  @Test
  void deleteByIdSuccessTest() {
    var post = postSetting();
    var postInterest = PostInterestFactory.createInterest(post);
    postInterestRepository.save(postInterest);
    var foundPostInterest = postInterestRepository
        .findByInterestedPostAndInterestingUserUuid(post, postInterest.getInterestingUserUuid())
        .orElse(null);
    assert foundPostInterest != null;

    postInterestRepository.deleteById(foundPostInterest.getId());

    var deletedPostInterest = postInterestRepository
        .findByInterestedPostAndInterestingUserUuid(
            post,
            foundPostInterest.getInterestingUserUuid()
        );
    Assertions.assertThat(deletedPostInterest).isEmpty();
  }
}