package com.pellto.youtoy.comment.application.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaDataRepository extends JpaRepository<CommentEntity, Long> {

}
