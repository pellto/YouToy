package com.pellto.youtoy.domain.post.repository;

import com.pellto.youtoy.domain.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
