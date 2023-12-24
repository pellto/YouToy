package com.pellto.youtoy.domain.community.repository;

import com.pellto.youtoy.domain.community.domain.CommunityPost;
import com.pellto.youtoy.domain.community.domain.PostInterest;
import com.pellto.youtoy.domain.user.domain.UserUUID;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityPostInterestRepository extends JpaRepository<PostInterest, Long> {

  Optional<PostInterest> findByInterestedPostAndInterestingUserUuid(CommunityPost interestingPost,
      UserUUID interestedUserUuid);

  List<PostInterest> findAllByInterestedPost(CommunityPost interestingPost);
}
