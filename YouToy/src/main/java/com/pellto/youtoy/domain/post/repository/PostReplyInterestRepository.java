package com.pellto.youtoy.domain.post.repository;

import com.pellto.youtoy.domain.base.repository.ReplyInterestRepository;
import com.pellto.youtoy.domain.post.domain.PostReply;
import com.pellto.youtoy.domain.post.domain.PostReplyInterest;

public interface PostReplyInterestRepository extends
    ReplyInterestRepository<PostReplyInterest, Long, PostReply> {

}
