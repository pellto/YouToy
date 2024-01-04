package com.pellto.youtoy.domain.post.repository;

import com.pellto.youtoy.domain.post.domain.Post;
import com.pellto.youtoy.domain.post.domain.PostComment;
import com.pellto.youtoy.domain.post.domain.PostReply;
import com.pellto.youtoy.domain.post.util.PostCommentFactory;
import com.pellto.youtoy.domain.post.util.PostFactory;
import com.pellto.youtoy.domain.post.util.PostReplyFactory;
import com.pellto.youtoy.domain.post.util.PostReplyInterestFactory;
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
class PostReplyInterestRepositoryTest {

  @Autowired
  private PostReplyInterestRepository replyInterestRepository;

  @Autowired
  private PostRepository postRepository;
  @Autowired
  private PostCommentRepository commentRepository;
  @Autowired
  private PostReplyRepository postReplyRepository;

  private Post postSetting() {
    var post = PostFactory.createPost();
    return postRepository.save(post);
  }

  private PostComment commentSetting() {
    var post = postSetting();
    var comment = PostCommentFactory.createCommunityComment(post);
    return commentRepository.save(comment);
  }

  private PostReply replySetting() {
    var comment = commentSetting();
    var reply = PostReplyFactory.create(comment);
    return postReplyRepository.save(reply);
  }

  @DisplayName("[replyInterestRepository: save: success] 답글 관심 저장 성공 테스트")
  @Test
  void saveSuccessTest() {
    var replyInterest = PostReplyInterestFactory.createBeforeSavedCommentInterest();

    var savedCommentInterest = replyInterestRepository.save(replyInterest);

    Assertions.assertThat(savedCommentInterest).isNotNull();
    Assertions.assertThat(savedCommentInterest.getId()).isNotNull();
    Assertions.assertThat(savedCommentInterest.getCreatedAt()).isNotNull();
  }

  @DisplayName("[replyInterestRepository: findAll: success] 답글 관심 commentId 전체 조회 성공 테스트")
  @Test
  void findAllByInterestedCommunityCommentSuccessTest() {
    var reply = replySetting();
    var replyInterest = PostReplyInterestFactory.createInterest(reply);
    replyInterestRepository.save(replyInterest);

    var replyInterests = replyInterestRepository.findAll();

    Assertions.assertThat(replyInterests).isNotNull();
    Assertions.assertThat(replyInterests).isNotEmpty();
    Assertions.assertThat(replyInterests.size()).isEqualTo(1);
  }

  @DisplayName("[replyInterestRepository: deleteById: success] 답글 관심 삭제 성공 테스트")
  @Test
  void deleteByIdSuccessTest() {
    var reply = replySetting();
    var replyInterest = PostReplyInterestFactory.createInterest(reply);
    replyInterestRepository.save(replyInterest);
    var foundReplyInterest = replyInterestRepository
        .findById(replyInterest.getId()).orElse(null);
    assert foundReplyInterest != null;

    replyInterestRepository.deleteById(foundReplyInterest.getId());

    var deletedCommentInterest = replyInterestRepository
        .findById(foundReplyInterest.getId());
    Assertions.assertThat(deletedCommentInterest).isEmpty();
  }
}