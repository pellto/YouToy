package com.pellto.youtoy.domain.community.repository;

import com.pellto.youtoy.domain.community.domain.CommunityComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityCommentRepository extends JpaRepository<CommunityComment, Long> {

}
