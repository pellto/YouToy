package com.pellto.youtoy.domain.community.repository;

import com.pellto.youtoy.domain.community.domain.CommunityComment;
import com.pellto.youtoy.domain.community.domain.CommunityCommentInterest;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityCommentInterestRepository extends
    JpaRepository<CommunityCommentInterest, Long> {

  List<CommunityCommentInterest> findAllByInterestedCommunityComment(CommunityComment comment);

  Optional<CommunityCommentInterest> findByInterestedCommunityCommentAndInterestingUserUuid(
      CommunityComment comment, UserUUID userUuid);
}
