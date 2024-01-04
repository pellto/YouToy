package com.pellto.youtoy.domain.post.repository;

import com.pellto.youtoy.domain.post.domain.Post;
import com.pellto.youtoy.domain.post.util.CommunityPostFactory;
import com.pellto.youtoy.domain.post.util.PostInterestFactory;
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
class PostInterestRepositoryTest {

  @Autowired
  private PostInterestRepository postInterestRepository;

  @Autowired
  private PostRepository postRepository;

  private Post postSetting() {
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

  @DisplayName("[postInterestRepository: findAllByInterestedContents: success] 포스트 관심 postId 전체 조회 성공 테스트")
  @Test
  void findAllByInterestedPostSuccessTest() {
    var post = postSetting();
    var postInterest = PostInterestFactory.createInterest(post);
    postInterestRepository.save(postInterest);

    var postInterests = postInterestRepository.findAllByInterestedContents(post);

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
        .findById(postInterest.getId())
        .orElse(null);
    assert foundPostInterest != null;

    postInterestRepository.deleteById(foundPostInterest.getId());

    var deletedPostInterest = postInterestRepository
        .findById(postInterest.getId());
    Assertions.assertThat(deletedPostInterest).isEmpty();
  }
}