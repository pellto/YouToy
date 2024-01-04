package com.pellto.youtoy.domain.post.repository;

import com.pellto.youtoy.domain.post.domain.Post;
import com.pellto.youtoy.domain.post.domain.PostComment;
import com.pellto.youtoy.domain.post.util.PostCommentFactory;
import com.pellto.youtoy.domain.post.util.PostCommentInterestFactory;
import com.pellto.youtoy.domain.post.util.PostFactory;
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
class PostCommentInterestRepositoryTest {

  @Autowired
  private PostCommentInterestRepository commentInterestRepository;

  @Autowired
  private PostRepository postRepository;
  @Autowired
  private PostCommentRepository commentRepository;

  private Post postSetting() {
    var post = PostFactory.createPost();
    return postRepository.save(post);
  }

  private PostComment commentSetting() {
    var post = postSetting();
    var comment = PostCommentFactory.createCommunityComment(post);
    return commentRepository.save(comment);
  }

  @DisplayName("[commentInterestRepository: save: success] 댓글 관심 저장 성공 테스트")
  @Test
  void saveSuccessTest() {
    var commentInterest = PostCommentInterestFactory.createBeforeSavedCommentInterest();

    var savedCommentInterest = commentInterestRepository.save(commentInterest);

    Assertions.assertThat(savedCommentInterest).isNotNull();
    Assertions.assertThat(savedCommentInterest.getId()).isNotNull();
    Assertions.assertThat(savedCommentInterest.getCreatedAt()).isNotNull();
  }

  @DisplayName("[commentInterestRepository: findAllByInterestedComment: success] 댓글 관심 commentId 전체 조회 성공 테스트")
  @Test
  void findAllByInterestedCommunityCommentSuccessTest() {
    var comment = commentSetting();
    var commentInterest = PostCommentInterestFactory.createInterest(comment);
    commentInterestRepository.save(commentInterest);

    var commentInterests = commentInterestRepository.findAllByInterestedComment(comment);

    Assertions.assertThat(commentInterests).isNotNull();
    Assertions.assertThat(commentInterests).isNotEmpty();
    Assertions.assertThat(commentInterests.size()).isEqualTo(1);
  }

  @DisplayName("[commentInterestRepository: deleteById: success] 댓글 관심 삭제 성공 테스트")
  @Test
  void deleteByIdSuccessTest() {
    var comment = commentSetting();
    var commentInterest = PostCommentInterestFactory.createInterest(comment);
    commentInterestRepository.save(commentInterest);
    var foundCommentInterest = commentInterestRepository
        .findById(commentInterest.getId())
        .orElse(null);
    assert foundCommentInterest != null;

    commentInterestRepository.deleteById(foundCommentInterest.getId());

    var deletedCommentInterest = commentInterestRepository
        .findById(foundCommentInterest.getId());
    Assertions.assertThat(deletedCommentInterest).isEmpty();
  }
}