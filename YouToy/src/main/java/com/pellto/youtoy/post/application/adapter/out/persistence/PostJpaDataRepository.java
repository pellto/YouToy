package com.pellto.youtoy.post.application.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaDataRepository extends JpaRepository<PostEntity, Long> {

}
