package com.pellto.youtoy.domain.post.repository;

import com.pellto.youtoy.domain.post.domain.Post;
import com.pellto.youtoy.domain.post.domain.PostComment;
import com.pellto.youtoy.domain.post.util.CommunityCommentFactory;
import com.pellto.youtoy.domain.post.util.CommunityPostFactory;
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
class PostReplyRepositoryTest {

  @Autowired
  private PostCommentRepository commentRepository;
  @Autowired
  private PostRepository postRepository;
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

  @DisplayName("[postReplyRepository: save: success] 커뮤니티 답글 저장 성공 테스트")
  @Test
  void saveSuccessTest() {
    var post = commentSetting();
    var replyComment = PostReplyCommentFactory.createBeforeSavedReplyComment(post);

    var savedReply = postReplyRepository.save(replyComment);

    Assertions.assertThat(savedReply).isNotNull();
    Assertions.assertThat(savedReply.getId()).isNotNull();
    Assertions.assertThat(savedReply.getLikeCount()).isEqualTo(0);
    Assertions.assertThat(savedReply.getCreatedAt()).isNotNull();
    Assertions.assertThat(savedReply.getModifiedAt()).isNotNull();
    Assertions.assertThat(savedReply.getModifiedAt()).isEqualTo(savedReply.getCreatedAt());
    Assertions.assertThat(savedReply.getCommenterUuid())
        .isEqualTo(replyComment.getCommenterUuid());
    Assertions.assertThat(savedReply.getParentComment())
        .isEqualTo(replyComment.getParentComment());
    Assertions.assertThat(savedReply.getCommentContent())
        .isEqualTo(replyComment.getCommentContent());
  }

  @DisplayName("[postReplyRepository: findAll: success] 커뮤니티 답글 전체 조회 성공 테스트")
  @Test
  void findAllSuccessTest() {
    var parentComment = commentSetting();
    var replyComment = PostReplyCommentFactory.createBeforeSavedReplyComment(parentComment);
    postReplyRepository.save(replyComment);

    var foundReplyList = postReplyRepository.findAll();

    Assertions.assertThat(foundReplyList).isNotNull();
    Assertions.assertThat(foundReplyList).isNotEmpty();
    Assertions.assertThat(foundReplyList.size()).isEqualTo(1);
  }

  @DisplayName("[postReplyRepository: findAllByParentComment: success] 커뮤니티 답글 parentComment 조건 조회 성공 테스트")
  @Test
  void findByIdSuccessTest() {
    var parentComment = commentSetting();
    var replyComment = PostReplyCommentFactory.createBeforeSavedReplyComment(parentComment);
    postReplyRepository.save(replyComment);
    var parentComment_2 = commentSetting();
    var replyComment_2 = PostReplyCommentFactory.createBeforeSavedReplyComment(parentComment_2);
    postReplyRepository.save(replyComment_2);

    var replies = postReplyRepository.findAllByParentComment(parentComment);

    Assertions.assertThat(replies).isNotNull();
    Assertions.assertThat(replies).isNotEmpty();
    Assertions.assertThat(replies.size()).isEqualTo(1);
  }
}