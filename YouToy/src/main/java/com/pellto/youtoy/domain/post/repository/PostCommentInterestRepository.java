package com.pellto.youtoy.domain.post.repository;

import com.pellto.youtoy.domain.base.repository.CommentInterestRepository;
import com.pellto.youtoy.domain.post.domain.PostComment;
import com.pellto.youtoy.domain.post.domain.PostCommentInterest;

public interface PostCommentInterestRepository extends
    CommentInterestRepository<PostCommentInterest, Long, PostComment> {

}
