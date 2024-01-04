package com.pellto.youtoy.domain.post.repository;

import com.pellto.youtoy.domain.post.domain.Post;
import com.pellto.youtoy.domain.post.domain.PostComment;
import com.pellto.youtoy.domain.post.domain.PostReply;
import com.pellto.youtoy.domain.post.util.CommunityCommentFactory;
import com.pellto.youtoy.domain.post.util.CommunityPostFactory;
import com.pellto.youtoy.domain.post.util.CommunityReplyCommentInterestFactory;
import com.pellto.youtoy.domain.post.util.PostReplyCommentFactory;
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
  private PostReplyInterestRepository replyCommentInterestRepository;

  @Autowired
  private PostRepository postRepository;
  @Autowired
  private PostCommentRepository commentRepository;
  @Autowired
  private PostReplyRepository postReplyRepository;

  private Post postSetting() {
    var post = CommunityPostFactory.createPost();
    return postRepository.save(post);
  }

  private PostComment commentSetting() {
    var post = postSetting();
    var comment = CommunityCommentFactory.createCommunityComment(post);
    return commentRepository.save(comment);
  }

  private PostReply replySetting() {
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

  @DisplayName("[replyCommentInterestRepository: findAll: success] 답글 관심 commentId 전체 조회 성공 테스트")
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
        .findById(replyCommentInterest.getId()).orElse(null);
    assert foundReplyCommentInterest != null;

    replyCommentInterestRepository.deleteById(foundReplyCommentInterest.getId());

    var deletedCommentInterest = replyCommentInterestRepository
        .findById(foundReplyCommentInterest.getId());
    Assertions.assertThat(deletedCommentInterest).isEmpty();
  }
}