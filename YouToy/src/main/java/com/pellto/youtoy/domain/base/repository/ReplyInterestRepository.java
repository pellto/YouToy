package com.pellto.youtoy.domain.base.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ReplyInterestRepository<RCI, ID, RC> extends JpaRepository<RCI, ID> {

  List<RCI> findAllByInterestedReply(RC reply);
}
