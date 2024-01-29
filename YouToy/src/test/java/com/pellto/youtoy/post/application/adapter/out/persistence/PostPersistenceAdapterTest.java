package com.pellto.youtoy.post.application.adapter.out.persistence;

import com.pellto.youtoy.post.util.PostFixtureFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import({PostPersistenceAdapter.class, PostMapper.class})
@Tag("persistenceAdapter")
class PostPersistenceAdapterTest {

  private static final String ADAPTER_NAME = "PostPersistenceAdapter";

  @Autowired
  private PostPersistenceAdapter postPersistenceAdapter;

  @DisplayName("[" + ADAPTER_NAME + "/save] 저장 성공 테스트")
  @Test
  void saveSuccessTest() {
    var beforeSaved = PostFixtureFactory.createBeforeSaved();
    var post = PostFixtureFactory.create(beforeSaved);

    var savedPost = postPersistenceAdapter.save(beforeSaved);

    Assertions.assertThat(savedPost).isNotNull();
    Assertions.assertThat(savedPost.getId()).isNotNull();
    Assertions.assertThat(savedPost.getCreatedAt()).isNotNull();
    Assertions.assertThat(savedPost).usingRecursiveComparison()
        .ignoringFields("createdAt", "updatedAt").isEqualTo(post);
  }

  @DisplayName("[" + ADAPTER_NAME + "/load] 읽기 성공 테스트")
  @Test
  void loadSuccessTest() {
    var beforeSaved = PostFixtureFactory.createBeforeSaved();
    var savedPost = postPersistenceAdapter.save(beforeSaved);

    var loadedPost = postPersistenceAdapter.load(savedPost.getId());

    Assertions.assertThat(loadedPost).isNotNull();
    Assertions.assertThat(loadedPost.getId()).isNotNull();
    Assertions.assertThat(loadedPost.getCreatedAt()).isNotNull();
    Assertions.assertThat(loadedPost).usingRecursiveComparison().isEqualTo(savedPost);
  }

  @DisplayName("[" + ADAPTER_NAME + "/update] 변경 성공 테스트")
  @Test
  void updateSuccessTest() {
    var beforeSaved = PostFixtureFactory.createBeforeSaved();
    var savedPost = postPersistenceAdapter.save(beforeSaved);
    var alreadyPost = postPersistenceAdapter.load(savedPost.getId());
    var alreadyUpdatedAt = alreadyPost.getUpdatedAt();
    var alreadyContent = alreadyPost.getPostContent().getContent();
    alreadyPost.changeContent("newContent");

    postPersistenceAdapter.update(alreadyPost);

    var changedPost = postPersistenceAdapter.load(alreadyPost.getId());

    Assertions.assertThat(changedPost).isNotNull();
    Assertions.assertThat(changedPost.getId()).isNotNull();
    Assertions.assertThat(changedPost.getCreatedAt()).isNotNull();
    Assertions.assertThat(changedPost).usingRecursiveComparison()
        .ignoringFields("postContent", "updatedAt")
        .isEqualTo(savedPost);
    Assertions.assertThat(changedPost.getPostContent().getTitle())
        .isEqualTo(savedPost.getPostContent().getTitle());
    Assertions.assertThat(changedPost.getPostContent().getContent()).isNotEqualTo(alreadyContent);
    Assertions.assertThat(changedPost.getUpdatedAt()).isNotEqualTo(alreadyUpdatedAt);
  }
}