package com.pellto.youtoy.domain.community.repository;

import com.pellto.youtoy.domain.community.domain.CommunityReplyCommentInterest;
import com.pellto.youtoy.domain.community.domain.PostReplyComment;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityReplyCommentInterestRepository extends
    JpaRepository<CommunityReplyCommentInterest, Long> {

  List<CommunityReplyCommentInterest> findAllByInterestedReplyComment(PostReplyComment comment);

  Optional<CommunityReplyCommentInterest> findByInterestedReplyCommentAndInterestingUserUuid(
      PostReplyComment comment, UserUUID userUuid);
}
