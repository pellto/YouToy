package com.pellto.youtoy.domain.community.repository;

import com.pellto.youtoy.domain.community.util.CommentCommentFactory;
import com.pellto.youtoy.domain.community.util.CommentPostFactory;
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
class CommunityCommentRepositoryTest {

  @Autowired
  private CommunityCommentRepository commentRepository;
  @Autowired
  private CommunityPostRepository postRepository;

  @DisplayName("[commentRepository: save: success] 커뮤니티 댓글 저장 성공 테스트")
  @Test
  void saveSuccessTest() {
    var communityComment = CommentCommentFactory.createBeforeSavedCommunityComment();

    var savedComment = commentRepository.save(communityComment);

    Assertions.assertThat(savedComment).isNotNull();
    Assertions.assertThat(savedComment.getId()).isNotNull();
    Assertions.assertThat(savedComment.getLikeCount()).isEqualTo(0);
    Assertions.assertThat(savedComment.getCreatedAt()).isNotNull();
    Assertions.assertThat(savedComment.getModifiedAt()).isNotNull();
    Assertions.assertThat(savedComment.getModifiedAt()).isEqualTo(savedComment.getCreatedAt());
    Assertions.assertThat(savedComment.getCommenterUuid())
        .isEqualTo(communityComment.getCommenterUuid());
    Assertions.assertThat(savedComment.getCommunityPost())
        .isEqualTo(communityComment.getCommunityPost());
    Assertions.assertThat(savedComment.getContent())
        .isEqualTo(communityComment.getContent());
  }

  @DisplayName("[commentRepository: findAll: success] 커뮤니티 댓글 전체 조회 성공 테스트")
  @Test
  void findAllSuccessTest() {
    var communityPost = CommentPostFactory.createBeforeSavedPost();
    postRepository.save(communityPost);
    var communityComment = CommentCommentFactory.createCommunityComment(communityPost);
    commentRepository.save(communityComment);

    var foundCommentList = commentRepository.findAll();

    Assertions.assertThat(foundCommentList).isNotNull();
    Assertions.assertThat(foundCommentList).isNotEmpty();
    Assertions.assertThat(foundCommentList.size()).isEqualTo(1);
  }

  @DisplayName("[commentRepository: findById: success] 커뮤니티 댓글 id 조건 조회 성공 테스트")
  @Test
  void findByIdSuccessTest() {
    var communityPost = CommentPostFactory.createBeforeSavedPost();
    postRepository.save(communityPost);
    var communityComment = CommentCommentFactory.createCommunityComment(communityPost);
    commentRepository.save(communityComment);

    var nullableComment = commentRepository.findById(communityComment.getId());

    Assertions.assertThat(nullableComment).isNotNull();
    Assertions.assertThat(nullableComment).isNotEmpty();
    var foundComment = nullableComment.get();
    Assertions.assertThat(foundComment.getId()).isEqualTo(communityComment.getId());
    Assertions.assertThat(foundComment.getCommunityPost())
        .isEqualTo(communityComment.getCommunityPost());
    Assertions.assertThat(foundComment.getCommenterUuid().getValue())
        .isEqualTo(communityComment.getCommenterUuid().getValue());
  }
}