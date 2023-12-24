package com.pellto.youtoy.domain.community.repository;

import com.pellto.youtoy.domain.community.domain.CommunityPost;
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
class CommunityPostRepositoryTest {

  @Autowired
  private CommunityPostRepository postRepository;

  @DisplayName("[commentPostRepository: save: success] 커뮤니티 포스트 저장 성공 테스트")
  @Test
  void saveSuccessTest() {
    var post = CommunityPostFactory.createBeforeSavedPost();

    var savedPost = postRepository.save(post);

    Assertions.assertThat(savedPost).isNotNull();
    Assertions.assertThat(savedPost.getId()).isNotNull();
    Assertions.assertThat(savedPost.getChannelId()).isEqualTo(post.getChannelId());
    Assertions.assertThat(savedPost.getCreatedAt()).isEqualTo(post.getCreatedAt());
    Assertions.assertThat(savedPost.getContent()).isEqualTo(post.getContent());
    Assertions.assertThat(savedPost.getModifiedAt()).isEqualTo(post.getModifiedAt());
  }

  @DisplayName("[commentPostRepository: findAll: success] 커뮤니티 포스트 전체 조회 성공 테스트")
  @Test
  void findAllSuccessTest() {
    var post = CommunityPostFactory.createBeforeSavedPost();
    postRepository.save(post);

    var foundPosts = postRepository.findAll();

    Assertions.assertThat(foundPosts).isNotEmpty();
    Assertions.assertThat(foundPosts.size()).isEqualTo(1);
    Assertions.assertThat(foundPosts.get(0).getClass()).isEqualTo(CommunityPost.class);
  }


  @DisplayName("[commentPostRepository: findById: success] 커뮤니티 포스트 id 조건 조회 성공 테스트")
  @Test
  void findByIdSuccessTest() {
    var post = CommunityPostFactory.createBeforeSavedPost();
    postRepository.save(post);

    var nullablePost = postRepository.findById(post.getId());

    Assertions.assertThat(nullablePost).isNotNull();
    Assertions.assertThat(nullablePost).isNotEmpty();
    var foundPost = nullablePost.get();
    Assertions.assertThat(foundPost.getChannelId()).isEqualTo(post.getChannelId());
    Assertions.assertThat(foundPost.getCreatedAt()).isEqualTo(post.getCreatedAt());
    Assertions.assertThat(foundPost.getContent()).isEqualTo(post.getContent());
    Assertions.assertThat(foundPost.getModifiedAt()).isEqualTo(post.getModifiedAt());
  }

}