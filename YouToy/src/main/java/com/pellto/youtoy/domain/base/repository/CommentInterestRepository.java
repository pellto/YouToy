package com.pellto.youtoy.domain.base.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CommentInterestRepository<CI, ID, CM> extends JpaRepository<CI, ID> {

  List<CI> findAllByInterestedComment(CM comment);
}
