package com.pellto.youtoy.domain.base.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ReplyRepository<RC, ID, CM> extends JpaRepository<RC, ID> {

  List<RC> findAllByParentComment(CM parentComment);
}
