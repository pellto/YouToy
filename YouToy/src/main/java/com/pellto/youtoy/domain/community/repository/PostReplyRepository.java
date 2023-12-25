package com.pellto.youtoy.domain.community.repository;

import com.pellto.youtoy.domain.community.domain.CommunityComment;
import com.pellto.youtoy.domain.community.domain.PostReplyComment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostReplyRepository extends JpaRepository<PostReplyComment, Long> {

  List<PostReplyComment> findAllByParentComment(CommunityComment parentComment);
}
