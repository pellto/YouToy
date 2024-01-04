package com.pellto.youtoy.domain.post.repository;

import com.pellto.youtoy.domain.base.repository.ContentsInterestRepository;
import com.pellto.youtoy.domain.post.domain.Post;
import com.pellto.youtoy.domain.post.domain.PostInterest;

public interface PostInterestRepository extends
    ContentsInterestRepository<PostInterest, Long, Post> {

}
