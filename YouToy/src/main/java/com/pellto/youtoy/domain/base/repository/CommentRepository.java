package com.pellto.youtoy.domain.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CommentRepository<C, ID> extends JpaRepository<C, ID> {

}
